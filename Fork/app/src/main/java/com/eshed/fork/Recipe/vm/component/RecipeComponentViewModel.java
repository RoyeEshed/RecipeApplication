package com.eshed.fork.Recipe.vm.component;

public interface RecipeComponentViewModel {
    enum Type {
        Direction,
        Header,
        Ingredient,
        Footer,
        Tag,
        Image
    }

    Type getType();
}