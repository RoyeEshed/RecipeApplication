package com.eshed.fork.Recipe.vm.component.Footer;

import com.eshed.fork.Recipe.vm.component.RecipeComponentIsEditable;
import com.eshed.fork.Recipe.vm.component.RecipeComponentViewModel;

public class DirectionFooterViewModel extends FooterViewModel implements RecipeComponentViewModel, RecipeComponentIsEditable {
    public int imageResource;

    public DirectionFooterViewModel(int imageResource, boolean isEditable) {
        super(isEditable);
        this.imageResource = imageResource;
    }

    @Override
    public RecipeComponentViewModel.Type getType() {
        return Type.Footer_Direction;
    }
}
