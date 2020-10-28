package com.eshed.fork.data;

import com.eshed.fork.data.model.Recipe;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class DbRecipeRepository implements RecipeRepository {
    @Override
    public Observable<List<Recipe>> getRecipes() {
        return null;
    }

    @Override
    public Single<Recipe> getRecipeWithID(int recipeID) {
        return null;
    }

    @Override
    public Recipe createNewRecipe() {
        return null;
    }

    @Override
    public Recipe createNewRecipeFromRecipe(Recipe recipe, String newName) {
        return null;
    }

    @Override
    public void saveRecipe(Recipe recipe) {

    }
}
