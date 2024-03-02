package com.oliverhalasz.recipie.ui.views;

import com.oliverhalasz.recipie.models.Recipe;
import com.oliverhalasz.recipie.services.recipeservices.RecipeService;
import com.oliverhalasz.recipie.ui.MainLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import jakarta.annotation.security.RolesAllowed;

import java.util.List;

@Route(value = "discover", layout = MainLayout.class)
@PageTitle("Discover | ReciPie")
@RolesAllowed("USER")
@UIScope
public class DiscoverView extends VerticalLayout {
    private final RecipeService recipeService;

    public DiscoverView(RecipeService recipeService) {
        this.recipeService = recipeService;

        List<Recipe> recipes = recipeService.getAllRecipesByAllUsers();
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

        H5 cookingTimeField = new H5("Preparation Time: " + recipe.getCookingTimeInMinutes());

        H5 difficultyLevelField = new H5("Difficulty Level: " + recipe.getDifficultyLevel());

        H5 authorField = new H5("Author: " + recipeService.getRecipeAuthor(recipe));

        HorizontalLayout recipeDetails = new HorizontalLayout();
        recipeDetails.add(cookingTimeField, difficultyLevelField, authorField);
        recipeDetails.addClassName("recipedetails");

        recipeLayout.add(titleField, descriptionField, recipeDetails);

        recipeLayout.addClickListener(event -> {
            //TODO:IMPLEMENTÁLNI EZT HOGY A RECEPT RÉSZLETEIHEZ VEZESSEN.
            //getUI().ifPresent(ui -> ui.navigate("recipe/" + recipe.getId()));
        });

        add(recipeLayout);
    }
}