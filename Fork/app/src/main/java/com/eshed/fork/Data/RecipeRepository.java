package com.eshed.fork.Data;

import com.eshed.fork.Data.model.Recipe;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public interface RecipeRepository {
    Observable<List<Recipe>> getRecipes();
    Single<Recipe> getRecipeWithID(int recipeID);

    Recipe createNewRecipe();
    Recipe createNewRecipeFromRecipe(Recipe recipe, String newName);

    void saveRecipe(Recipe recipe);
}
