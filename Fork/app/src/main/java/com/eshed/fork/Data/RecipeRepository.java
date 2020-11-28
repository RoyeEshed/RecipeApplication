package com.eshed.fork.Data;

import com.eshed.fork.Data.model.Recipe;
import com.eshed.fork.Data.model.UserAccount;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public interface RecipeRepository {
    Observable<List<Recipe>> retrieveRecipes();
    Single<Recipe> getRecipeWithID(int recipeID);
    int numberOfChildren(Recipe recipe);
    Recipe createNewRecipe();
    Recipe createNewRecipeFromRecipe(Recipe recipe, String newName);

    void saveRecipe(Recipe recipe, String uid);

    List<Recipe> getRecipesSubmittedByUser(String uid);
    List<Recipe> getRecipesStarredByUser(String uid);
    void saveUser(UserAccount user);
    Single<UserAccount> getUserWithUID(String uid);
}
