package com.oliverhalasz.recipie.ui.events;

import com.oliverhalasz.recipie.ui.views.RecipeView;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.DomEvent;

@DomEvent("reload-recipes")
public class ReloadRecipesEvent extends ComponentEvent<RecipeView> {
    public ReloadRecipesEvent(RecipeView source, boolean fromClient) {
        super(source, fromClient);
    }

    public static class ReloadRecipesListener implements ComponentEventListener<ReloadRecipesEvent> {
        @Override
        public void onComponentEvent(ReloadRecipesEvent event) {
            event.getSource().loadRecipes();
        }
    }
}
