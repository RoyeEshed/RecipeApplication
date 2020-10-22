package com.eshed.fork.data;

import com.eshed.fork.data.model.Recipe;

import java.util.List;

public interface RecipeRepository {
    List<Recipe> getRecipes();
    Recipe getRecipeWithID(int recipeID);

    Recipe createNewRecipe();
    Recipe createNewRecipeFromRecipe(Recipe recipe, String newName);

    void saveRecipe(Recipe recipe);
}
