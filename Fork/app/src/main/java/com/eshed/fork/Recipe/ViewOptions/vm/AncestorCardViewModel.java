package com.eshed.fork.Recipe.ViewOptions.vm;

import com.eshed.fork.Data.model.Recipe;

public class AncestorCardViewModel {
    private Recipe recipe;

    public AncestorCardViewModel(Recipe recipe) {
        this.recipe = recipe;
    }

    public Recipe getRecipe() {
        return recipe;
    }
}
