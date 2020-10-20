package com.eshed.fork.data.model;

import java.util.List;

public class Recipe {
    private int recipeID;
    private String name;
    private String imageURL;
    private String contributor;
    private List<Direction> directions;
    private List<Ingredient> ingredients;
    private List<String> tags;
    private int parentRecipeID;

    public Recipe(int recipeID, String name, String imageURL, String contributor, List<Ingredient> ingredients, List<Direction> directions, List<String> tags) {
        this(recipeID, name, imageURL, contributor, ingredients, directions, tags, -1);
    }

    public Recipe(int recipeID, String name, String imageURL, String contributor, List<Ingredient> ingredients, List<Direction> directions, List<String> tags, int parentRecipeID) {
        this.recipeID = recipeID;
        this.name = name;
        this.imageURL = imageURL;
        this.contributor = contributor;
        this.ingredients = ingredients;
        this.directions = directions;
        this.tags = tags;
        this.parentRecipeID = parentRecipeID;
    }

    public int getRecipeID() {
        return recipeID;
    }

    public String getName() {
        return name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public List<Direction> getDirections() {
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

    public int getParentRecipeID() {
        return parentRecipeID;
    }

    public void setParentRecipeID(int parentRecipeID) {
        this.parentRecipeID = parentRecipeID;
    }
}
