package com.eshed.fork.Recipe.vm.component;

public class FooterViewModel implements RecipeComponentViewModel, RecipeComponentIsEditable {
    public int imageResource;
    public boolean isEditable;

    public FooterViewModel(int imageResource, boolean isEditable) {
        this.imageResource = imageResource;
        this.isEditable = isEditable;
    }

    @Override
    public Type getType() {
        return Type.Footer;
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
