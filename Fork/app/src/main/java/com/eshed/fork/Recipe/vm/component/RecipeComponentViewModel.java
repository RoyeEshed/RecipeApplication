package com.eshed.fork.Recipe.vm.component;

public interface RecipeComponentViewModel {
    enum Type {
        Direction,
        Header,
        Ingredient,
        Footer_Ingredient,
        Footer_Direction,
        Tag,
        Image
    }

    Type getType();
}