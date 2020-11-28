package com.eshed.fork.Favorites.vm;

import com.eshed.fork.Data.model.Recipe;

public class FavoritesCardViewModel {
    private Recipe recipe;

    public FavoritesCardViewModel(Recipe recipe) {
        this.recipe = recipe;
    }

    public Recipe getRecipe() {
        return recipe;
    }

}
