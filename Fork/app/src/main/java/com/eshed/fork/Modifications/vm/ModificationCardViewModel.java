package com.eshed.fork.Modifications.vm;

import com.eshed.fork.Data.model.Recipe;

public class ModificationCardViewModel {
    private Recipe recipe;

    public ModificationCardViewModel(Recipe recipe) {
        this.recipe = recipe;
    }

    public Recipe getRecipe() {
        return recipe;
    }

}