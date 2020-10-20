package com.eshed.fork.Recipe.vm.component;

public class TagViewModel implements RecipeComponentViewModel, RecipeComponentIsEditable {
    public String tag;
    public boolean isEditable;

    public TagViewModel(String tag) {
        this.tag = tag;
    }

    @Override
    public Type getType() {
        return Type.Tag;
    }

    @Override
    public void update(String update) {

    }

    @Override
    public void setIsEditable(boolean isEditable) {
        this.isEditable = isEditable;
    }

    @Override
    public Boolean isEditable() {
        return isEditable;
    }
}
