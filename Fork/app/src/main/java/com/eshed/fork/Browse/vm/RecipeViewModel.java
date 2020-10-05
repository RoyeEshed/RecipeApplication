package com.eshed.fork.Browse.vm;

import androidx.lifecycle.ViewModel;

import com.eshed.fork.Recipe;

public class RecipeViewModel extends ViewModel {
    private Recipe recipe;

    public RecipeViewModel(Recipe recipe) {
        this.recipe = recipe;
    }

    public Recipe getRecipe() {
        return recipe;
    }
}
