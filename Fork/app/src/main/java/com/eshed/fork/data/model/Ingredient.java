package com.eshed.fork.data.model;

public class Ingredient {
    String amount;
    String ingredientName;

    public Ingredient(String amount, String ingredientName) {
        this.amount = amount;
        this.ingredientName = ingredientName;
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
