package com.eshed.fork.Recipe.vm.component;

public class TagViewModel implements RecipeInformation {
    public String tag;

    public TagViewModel(String tag) {
        this.tag = tag;
    }

    @Override
    public Type getType() {
        return Type.Tag;
    }
}
