package com.eshed.fork.data.model;

public class Recipe {
    private String name;
    private int imageResource;

    public Recipe(String name, int imageResource) {
        this.name = name;
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public int getImageResource() {
        return imageResource;
    }
}
