package com.eshed.fork.StarredRecipes.vm;

import com.eshed.fork.Data.model.Recipe;

public class StarredRecipeCardViewModel {
    private Recipe recipe;

    public StarredRecipeCardViewModel(Recipe recipe) {
        this.recipe = recipe;
    }

    public Recipe getRecipe() {
        return recipe;
    }

}
