package com.eshed.fork.Recipe.vm.component;

import java.util.Arrays;
import java.util.List;

public class TagViewModel implements RecipeComponentViewModel, RecipeComponentIsEditable {
    public List<String> tags;
    public boolean isEditable;

    public TagViewModel(List<String> tags, boolean isEditable) {
        this.tags = tags;
        this.isEditable = isEditable;
    }

    public void changeTags(String tags) {
        List<String> tagsList = Arrays.asList(tags.split(", "));
        this.tags.clear();
        for (String s: tagsList) {
            this.tags.add(s);
        }
    }

    @Override
    public Type getType() {
        return Type.Tag;
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
