package com.eshed.fork.data;

import com.eshed.fork.Recipe;

import java.util.List;

public interface RecipeRepository {
    List<Recipe> getRecipes();
    Recipe getRecipe(String recipeName);
}
