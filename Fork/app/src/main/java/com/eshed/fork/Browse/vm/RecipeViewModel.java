package com.eshed.fork.Browse.vm;

import com.eshed.fork.data.model.Recipe;

public class RecipeViewModel {
    private Recipe recipe;

    public RecipeViewModel(Recipe recipe) {
        this.recipe = recipe;
    }

    public Recipe getRecipe() {
        return recipe;
    }
}
