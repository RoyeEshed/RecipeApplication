package com.eshed.fork.Recipe.vm.component;

public class ImageViewModel implements RecipeComponentViewModel, RecipeComponentIsEditable {
    public String imageURL;
    public boolean isEditable;

    public ImageViewModel(String imageURL, boolean isEditable) {
        this.imageURL = imageURL;
        this.isEditable = isEditable;
    }

    @Override
    public Type getType() {
        return Type.Image;
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
