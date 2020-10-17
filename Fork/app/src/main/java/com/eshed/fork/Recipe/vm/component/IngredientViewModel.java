package com.eshed.fork.Recipe.vm.component;
import com.eshed.fork.data.model.Ingredient;

public class IngredientViewModel implements RecipeComponentViewModel, RecipeComponentIsEditable {
    public Ingredient ingredient;
    public boolean isEditable;

    public IngredientViewModel(Ingredient ingredient, Boolean isEditable) {
        this.ingredient = ingredient;
        this.isEditable = isEditable;
    }

    @Override
    public Type getType() {
        return Type.Ingredient;
    }

    @Override
    public void setIsEditable(Boolean isEditable) {
        this.isEditable = isEditable;
    }

    @Override
    public Boolean isEditable() {
        return isEditable;
    }
}
