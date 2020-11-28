package com.eshed.fork.Browse.vm;

import com.eshed.fork.Data.model.Ingredient;
import com.eshed.fork.Data.model.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeCardViewModel {
    private Recipe recipe;
    private int children;

    public RecipeCardViewModel(Recipe recipe, int children) {
        this.recipe = recipe;
        this.children = children;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public int getChildren() {
        return children;
    }

    public List<String> getSearchTerms() {
        List<String> searchTerms = new ArrayList<>();

        searchTerms.add(recipe.getName().toLowerCase().trim());
        for (Ingredient i: recipe.getIngredients()) {
            searchTerms.add(i.getIngredientName().toLowerCase().trim());
        }
        for (String s: recipe.getTags()) {
            searchTerms.add(s.toLowerCase().trim());
        }

        return searchTerms;
    }
}
