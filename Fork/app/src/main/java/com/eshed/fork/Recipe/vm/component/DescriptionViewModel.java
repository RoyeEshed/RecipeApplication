package com.eshed.fork.Recipe.vm.component;

import java.util.List;

public class DescriptionViewModel implements RecipeComponentViewModel, RecipeComponentIsEditable {
    public List<String> description;
    public Boolean isEditable;

    public DescriptionViewModel(List<String> description, boolean isEditable) {
        this.description = description;
        this.isEditable = isEditable;
    }

    @Override
    public Type getType() {
        return Type.Description;
    }

    @Override
    public void setIsEditable(boolean isEditable) {
        this.isEditable = isEditable;
    }

    @Override
    public boolean isEditable() {
        return isEditable;
    }

    public void changeDescription(String newDescription) {
        if (description.isEmpty()) {
            description.add(newDescription);
        } else {
            this.description.set(0, newDescription);
        }
    }
}