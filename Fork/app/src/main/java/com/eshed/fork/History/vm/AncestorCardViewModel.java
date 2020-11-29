package com.eshed.fork.History.vm;

import com.eshed.fork.Data.model.Recipe;

public class AncestorCardViewModel {
    private Recipe recipe;
    private boolean hasEmphasis;

    public AncestorCardViewModel(Recipe recipe) {
        this.recipe = recipe;
        this.hasEmphasis = false;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public boolean hasEmphasis() {
        return hasEmphasis;
    }

    public void setEmphasis(boolean hasEmphasis) {
        this.hasEmphasis = hasEmphasis;
    }
}
