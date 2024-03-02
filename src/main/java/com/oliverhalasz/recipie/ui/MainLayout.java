package com.oliverhalasz.recipie.ui;

import com.oliverhalasz.recipie.services.recipeservices.RecipeAddingService;
import com.oliverhalasz.recipie.services.SecurityService;
import com.oliverhalasz.recipie.ui.views.Discover;
import com.oliverhalasz.recipie.ui.views.RecipeView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

@SpringComponent
@UIScope
public class MainLayout extends AppLayout {

    private final RecipeAddingService recipeAddingService;
    private final SecurityService securityService;

    public MainLayout(RecipeAddingService recipeAddingService, SecurityService securityService) {
        this.recipeAddingService = recipeAddingService;
        this.securityService = securityService;

        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1("ReciPie Application");
        logo.addClassName("logo");

        String username = securityService.getAuthenticatedUser().getUsername();
        Button logout = new Button("Log out " + username, e -> securityService.logout());

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo, logout);
        header.setWidth("100%");
        header.addClassName("header");

        addToNavbar(header);
    }

    private void createDrawer() {
        RouterLink recipeLink = new RouterLink("My recipes", RecipeView.class);
        Button addButton = new Button("Add Recipe");
        addButton.addClickListener(e -> recipeAddingService.openAddRecipeDialog());
        RouterLink discoverRecipesLink = new RouterLink("Discover recipes", Discover.class);

        addToDrawer(recipeLink, discoverRecipesLink, addButton);
    }
}