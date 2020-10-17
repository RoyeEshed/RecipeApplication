package com.eshed.fork.Recipe.vm.component;
import com.eshed.fork.data.model.Ingredient;

public class IngredientViewModel implements RecipeInformation {
    public Ingredient ingredient;

    public IngredientViewModel(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public Type getType() {
        return Type.Ingredient;
    }
}
