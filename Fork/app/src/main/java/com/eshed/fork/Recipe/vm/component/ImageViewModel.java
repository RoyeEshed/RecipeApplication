package com.eshed.fork.Recipe.vm.component;

public class ImageViewModel implements RecipeComponentViewModel {
    public String imageURL;

    public ImageViewModel(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public Type getType() {
        return Type.Image;
    }

    @Override
    public void update(String update) {

    }
}
