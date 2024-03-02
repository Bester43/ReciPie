package com.oliverhalasz.recipie.repositories;

import com.oliverhalasz.recipie.models.Recipe;
import com.oliverhalasz.recipie.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findByUser(User user);
}