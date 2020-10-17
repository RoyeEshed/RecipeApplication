package com.eshed.fork.Recipe.vm.component;

// TODO: RecipeComponentViewModel
public interface RecipeInformation {
    enum Type {
        Direction,
        Header,
        Ingredient,
        Footer,
        Tag
    }

    public Type getType();
}