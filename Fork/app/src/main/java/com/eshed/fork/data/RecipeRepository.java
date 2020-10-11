package com.eshed.fork.data;

import com.eshed.fork.data.model.Recipe;

import java.util.List;

public interface RecipeRepository {
    List<Recipe> getRecipes();
    Recipe getRecipeWithName(String recipeName);
    Recipe getRecipeWithID(int recipeID);
}
