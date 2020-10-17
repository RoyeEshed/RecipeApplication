package com.eshed.fork.data.model;

public class Ingredient {
    String amount;
    String ingredientName;
    int recipeID;

    public Ingredient(String amount, String ingredientName, int recipeID) {
        this.amount = amount;
        this.ingredientName = ingredientName;
        this.recipeID = recipeID;
    }

    // temp
    public Ingredient(String amount, String ingredientName) {
        this(amount, ingredientName, -1);
    }

    public String getAmount() {
        return amount;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public String toString() {
        return amount + " – " + ingredientName;
    }
}
