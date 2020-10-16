package com.eshed.fork.data.model;

import java.util.List;

public class Recipe {
    private int recipeID;
    private String name;
    private int imageResource;
    private String contributor;
    private List<String> directions;
    private List<Ingredient> ingredients;
    private List<String> tags;

    public Recipe(int recipeID, String name, int imageResource, String contributor, List<Ingredient> ingredients, List<String> directions, List<String> tags) {
        this.recipeID = recipeID;
        this.name = name;
        this.imageResource = imageResource;
        this.contributor = contributor;
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

    public List<String> getDirections() {
        return directions;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getContributor() {
        return contributor;
    }
}
