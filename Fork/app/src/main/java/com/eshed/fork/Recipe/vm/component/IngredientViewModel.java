package com.eshed.fork.Recipe.vm.component;
import com.eshed.fork.data.model.Ingredient;

public class IngredientViewModel implements RecipeComponentViewModel, RecipeComponentIsEditable {
    public Ingredient ingredient;
    public boolean isEditable;

    public IngredientViewModel(Ingredient ingredient, boolean isEditable) {
        this.ingredient = ingredient;
        this.isEditable = isEditable;
    }

    public void changeIngredientName(String ingredientName) {
        ingredient.setIngredientName(ingredientName);
    }

    public void changeAmount(String amount) {
        ingredient.setAmount(amount);
    }

    @Override
    public Type getType() {
        return Type.Ingredient;
    }

    @Override
    public void setIsEditable(boolean isEditable) {
        this.isEditable = isEditable;
    }

    @Override
    public boolean isEditable() {
        return isEditable;
    }
}
