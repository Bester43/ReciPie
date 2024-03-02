package com.oliverhalasz.recipie.services.recipeservices;

import com.oliverhalasz.recipie.models.Recipe;
import com.oliverhalasz.recipie.models.User;
import com.oliverhalasz.recipie.repositories.RecipeRepository;
import com.oliverhalasz.recipie.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final UserService userService;

    public String getRecipeAuthor(Recipe recipe) {
        return recipe.getUser().getUsername();
    }


    public List<Recipe> getAllRecipes() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.findByUsername(username);
        return recipeRepository.findByUser(currentUser);
    }

    public List<Recipe> getAllRecipesByAllUsers() {
        return recipeRepository.findAll();
    }

    public Recipe getRecipeWithId(Long id) {
        return recipeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Recipe not found with id " + id));
    }

    public void saveRecipe(Recipe recipe) {
        recipeRepository.save(recipe);
    }

    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }
}
