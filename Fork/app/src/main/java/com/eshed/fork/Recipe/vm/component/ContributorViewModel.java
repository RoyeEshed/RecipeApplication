package com.eshed.fork.Recipe.vm.component;

public class ContributorViewModel implements RecipeComponentViewModel, RecipeComponentIsEditable {
    public String contributor;
    public boolean isStarred;
    public boolean isEditable;

    public ContributorViewModel(String contributor, boolean isEditable, boolean isStarred) {
        this.contributor = contributor;
        this.isEditable = isEditable;
        this.isStarred = isStarred;
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
    public boolean isEditable() {
        return isEditable;
    }
}
