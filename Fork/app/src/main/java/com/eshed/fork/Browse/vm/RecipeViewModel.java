package com.eshed.fork.Browse.vm;

import com.eshed.fork.data.RecipeRepository;
import com.eshed.fork.data.model.Recipe;

public class RecipeViewModel {
    private Recipe recipe;
    private RecipeRepository recipeRepository;


    public RecipeViewModel(Recipe recipe) {
        this.recipe = recipe;
    }

    public RecipeViewModel(int recipeID, RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
        recipe = recipeRepository.getRecipeWithID(recipeID);

    }

    public Recipe getRecipe() {
        return recipe;
    }
}
