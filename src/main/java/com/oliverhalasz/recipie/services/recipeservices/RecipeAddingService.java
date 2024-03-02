package com.oliverhalasz.recipie.services.recipeservices;

import com.oliverhalasz.recipie.models.Recipe;
import com.oliverhalasz.recipie.models.User;
import com.oliverhalasz.recipie.services.UserService;
import com.oliverhalasz.recipie.ui.events.ReloadRecipesEvent;
import com.oliverhalasz.recipie.ui.views.RecipeView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import static com.vaadin.flow.component.ComponentUtil.fireEvent;

@SpringComponent
@UIScope
public class RecipeAddingService {

    private final RecipeService recipeService;
    private final UserService userService;
    private final RecipeView recipeView;

    @Autowired
    public RecipeAddingService(RecipeService recipeService, UserService userService, RecipeView recipeView) {
        this.recipeService = recipeService;
        this.userService = userService;
        this.recipeView = recipeView;
    }

    public void openAddRecipeDialog() {
        Dialog dialog = new Dialog();
        dialog.setCloseOnOutsideClick(false);
        dialog.addClassName("recipe-dialog");

        dialog.setHeaderTitle("New Recipe");

        VerticalLayout dialogLayout = new VerticalLayout();
        dialogLayout.setPadding(true);
        dialogLayout.setSpacing(true);
        dialogLayout.setWidth("400px");

        TextField titleField = new TextField("Title");

        IntegerField cookingTimeField = new IntegerField("Cooking Time");
        cookingTimeField.setHelperText("Minutes");
        cookingTimeField.setValue(10);
        cookingTimeField.setStepButtonsVisible(true);

        TextArea descriptionField = new TextArea("Description");

        Select<String> difficultyLevelField = new Select<>();
        difficultyLevelField.setLabel("Difficulty Level");
        difficultyLevelField.setItems("Easy", "Medium", "Hard");

        Button saveButton = new Button("Save", event -> {
            if (validateFields(titleField, cookingTimeField, difficultyLevelField, descriptionField)) {
                Recipe recipe = new Recipe();
                recipe.setTitle(titleField.getValue());
                recipe.setDescription(descriptionField.getValue());
                recipe.setCookingTimeInMinutes(cookingTimeField.getValue());
                recipe.setDifficultyLevel(difficultyLevelField.getValue());

                UserDetails currentUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                User user = userService.findByUsername(currentUser.getUsername());
                recipe.setUser(user);

                recipeService.saveRecipe(recipe);
                Notification.show("Recipe saved successfully");
                dialog.close();
                fireEvent(recipeView, new ReloadRecipesEvent(recipeView, false));
            }
        });

        Button cancelButton = new Button("Cancel", event -> dialog.close());


        dialogLayout.add(titleField, cookingTimeField, difficultyLevelField, descriptionField);
        dialog.add(dialogLayout);
        dialog.getFooter().add(cancelButton);
        dialog.getFooter().add(saveButton);
        dialog.open();
    }

    private boolean validateFields(TextField titleField, IntegerField cookingTimeField, Select<String> difficultyLevelField, TextArea descriptionField) {
        if (titleField.isEmpty() || cookingTimeField.isEmpty() || difficultyLevelField.isEmpty() || descriptionField.isEmpty()) {
            Notification.show("All fields are required", 3000, Notification.Position.TOP_CENTER);
            return false;
        }
        return true;
    }
}
