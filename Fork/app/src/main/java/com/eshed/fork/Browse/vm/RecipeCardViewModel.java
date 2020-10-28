package com.eshed.fork.Browse.vm;

import com.eshed.fork.data.model.Recipe;

public class RecipeCardViewModel {
    private Recipe recipe;

    public RecipeCardViewModel(Recipe recipe) {
        this.recipe = recipe;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public int getRecipeID() {
        return recipe.getRecipeID();
    }
}
