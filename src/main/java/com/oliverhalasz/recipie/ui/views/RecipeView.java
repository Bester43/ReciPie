package com.oliverhalasz.recipie.ui.views;

import com.oliverhalasz.recipie.ui.events.ReloadRecipesEvent;
import com.oliverhalasz.recipie.models.Recipe;
import com.oliverhalasz.recipie.services.recipeservices.RecipeService;
import com.oliverhalasz.recipie.ui.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.stereotype.Component;

import java.util.List;

@Route(value = "", layout = MainLayout.class)
@PageTitle("My recipes | ReciPie")
@RolesAllowed("USER")
@UIScope
@Component
public class RecipeView extends VerticalLayout {

    private final RecipeService recipeService;

    public RecipeView(RecipeService recipeService) {
        this.recipeService = recipeService;

        loadRecipes();
        registerEventListeners();
    }

    public void loadRecipes() {
        List<Recipe> recipes = recipeService.getAllRecipes();
        removeAll();
        for (Recipe recipe : recipes) {
            addOrUpdateRecipeLayout(recipe);
        }
    }

    public void addOrUpdateRecipeLayout(Recipe recipe) {
        VerticalLayout recipeLayout = new VerticalLayout();
        recipeLayout.addClassName("recipe-card");

        H1 titleField = new H1(recipe.getTitle());
        titleField.getStyle().set("font-weight", "bold");

        H3 descriptionField = new H3(recipe.getDescription());

        H5 cookingTimeField = new H5("Preparation Time: " + String.valueOf(recipe.getCookingTimeInMinutes()));

        H5 difficultyLevelField = new H5("Difficulty Level: " + recipe.getDifficultyLevel());

        Button removeButton = new Button("Remove");
        removeButton.addClickListener(c -> {
            recipeService.deleteRecipe(recipe.getId());
            loadRecipes();
        });

        recipeLayout.add(titleField, descriptionField, new HorizontalLayout(cookingTimeField, difficultyLevelField), removeButton);

        add(recipeLayout);
    }

    private void registerEventListeners() {
        addListener(ReloadRecipesEvent.class, new ReloadRecipesEvent.ReloadRecipesListener());
    }
}