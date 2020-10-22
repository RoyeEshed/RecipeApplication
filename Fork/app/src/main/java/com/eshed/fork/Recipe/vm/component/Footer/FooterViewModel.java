package com.eshed.fork.Recipe.vm.component.Footer;

import com.eshed.fork.Recipe.vm.component.RecipeComponentIsEditable;
import com.eshed.fork.Recipe.vm.component.RecipeComponentViewModel;

public abstract class FooterViewModel implements RecipeComponentIsEditable, RecipeComponentViewModel {
    public int imageResource;
    private boolean isEditable;

    public abstract RecipeComponentViewModel.Type getType();

    FooterViewModel(boolean isEditable) {
        this.isEditable = isEditable;
    }

    public int getImageResource() {
        return imageResource;
    };

    @Override
    public void setIsEditable(boolean isEditable) {
        this.isEditable = isEditable;
    }

    @Override
    public boolean isEditable() {
        return isEditable;
    }
}
