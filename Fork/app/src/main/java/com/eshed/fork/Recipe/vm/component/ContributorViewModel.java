package com.eshed.fork.Recipe.vm.component;

public class ContributorViewModel implements RecipeComponentViewModel, RecipeComponentIsEditable {
    public String contributor;
    public boolean isEditable;

    public ContributorViewModel(String contributor, boolean isEditable) {
        this.contributor = contributor;
        this.isEditable = isEditable;
    }

    @Override
    public Type getType() {
        return Type.Contributor;
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
