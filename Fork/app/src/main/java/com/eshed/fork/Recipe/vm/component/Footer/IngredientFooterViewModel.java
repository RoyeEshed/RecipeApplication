package com.eshed.fork.Recipe.vm.component.Footer;

import com.eshed.fork.Recipe.vm.component.RecipeComponentIsEditable;
import com.eshed.fork.Recipe.vm.component.RecipeComponentViewModel;

public class IngredientFooterViewModel implements RecipeComponentViewModel, RecipeComponentIsEditable, FooterViewModel{
    public int imageResource;
    public boolean isEditable;

    public IngredientFooterViewModel(int imageResource, boolean isEditable) {
        this.imageResource = imageResource;
        this.isEditable = isEditable;
    }

    @Override
    public Type getType() {
        return Type.Footer;
    }

    @Override
    public FooterType getFooterType() {
        return FooterType.Ingredient_Footer;
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
