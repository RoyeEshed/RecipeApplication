package com.eshed.fork.data.model;

public class Recipe {
    private int recipeID;
    private String name;
    private int imageResource;
    private String directions;
    private String ingredients;
    private String tags;

    public Recipe(int recipeID, String name, int imageResource, String ingredients, String directions, String tags) {
        this.recipeID = recipeID;
        this.name = name;
        this.imageResource = imageResource;
        this.ingredients = ingredients;
        this.directions = directions;
        this.tags = tags;
    }

    public int getRecipeID() {
        return recipeID;
    }

    public String getName() {
        return name;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getDirections() {
        return directions;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getTags() {
        return tags;
    }
}
