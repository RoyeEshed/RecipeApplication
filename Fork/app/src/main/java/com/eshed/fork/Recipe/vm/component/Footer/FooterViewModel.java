package com.eshed.fork.Recipe.vm.component.Footer;

import com.eshed.fork.Recipe.vm.component.RecipeComponentIsEditable;
import com.eshed.fork.Recipe.vm.component.RecipeComponentViewModel;

public abstract class FooterViewModel implements RecipeComponentIsEditable, RecipeComponentViewModel {
    public int imageResource;
    public boolean isEditable;
    public abstract RecipeComponentViewModel.Type getType();

    public int getImageResource() {
        return imageResource;
    };

    @Override
    public void setIsEditable(boolean isEditable) {
        this.isEditable = isEditable;
    }

    @Override
    public Boolean isEditable() {
        return isEditable;
    }
}
