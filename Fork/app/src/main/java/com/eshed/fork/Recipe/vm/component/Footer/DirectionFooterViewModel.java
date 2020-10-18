package com.eshed.fork.Recipe.vm.component.Footer;

import com.eshed.fork.Recipe.vm.component.RecipeComponentIsEditable;
import com.eshed.fork.Recipe.vm.component.RecipeComponentViewModel;

public class DirectionFooterViewModel extends FooterViewModel implements RecipeComponentViewModel, RecipeComponentIsEditable {
    public int imageResource;
    public boolean isEditable;

    public DirectionFooterViewModel(int imageResource, boolean isEditable) {
        this.imageResource = imageResource;
        this.isEditable = isEditable;
    }

    @Override
    public RecipeComponentViewModel.Type getType() {
        return Type.Footer_Direction;
    }

}
