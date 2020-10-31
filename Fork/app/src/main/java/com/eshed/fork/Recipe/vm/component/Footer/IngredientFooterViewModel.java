package com.eshed.fork.Recipe.vm.component.Footer;

import com.eshed.fork.Recipe.vm.component.RecipeComponentIsEditable;
import com.eshed.fork.Recipe.vm.component.RecipeComponentViewModel;

public class IngredientFooterViewModel extends FooterViewModel implements RecipeComponentViewModel, RecipeComponentIsEditable{
    public int imageResource;

    public IngredientFooterViewModel(int imageResource, boolean isEditable) {
        super(isEditable);
        this.imageResource = imageResource;
    }

    @Override
    public Type getType() {
        return Type.Footer_Ingredient;
    }
}
