package com.eshed.fork.Recipe.vm.component;

public class ImageViewModel implements RecipeComponentViewModel {
    public int imageResource;

    public ImageViewModel(int imageResource) {
        this.imageResource = imageResource;
    }

    @Override
    public Type getType() {
        return Type.Image;
    }
}
