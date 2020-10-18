package com.eshed.fork.Recipe.vm.component.Footer;

import com.eshed.fork.Recipe.vm.component.RecipeComponentViewModel;

public class DirectionFooterViewModel implements FooterViewModel {
    public int imageResource;
    public boolean isEditable;

    public DirectionFooterViewModel(int imageResource, boolean isEditable) {
        this.imageResource = imageResource;
        this.isEditable = isEditable;
    }

    @Override
    public RecipeComponentViewModel.Type getType() {
        return RecipeComponentViewModel.Type.Footer;
    }

    @Override
    public FooterType getFooterType() {
        return FooterType.Direction_Footer;
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
