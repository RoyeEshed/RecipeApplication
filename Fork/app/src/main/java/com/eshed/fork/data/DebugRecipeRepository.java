package com.eshed.fork.data;

import com.eshed.fork.R;
import com.eshed.fork.data.model.Recipe;

import java.util.Arrays;
import java.util.List;

public class DebugRecipeRepository implements RecipeRepository {
    private static DebugRecipeRepository instance = new DebugRecipeRepository();

    public static DebugRecipeRepository getInstance() {
        return instance;
    }

    private Recipe[] recipes  = {
            new Recipe("Recipe 1", R.drawable.ic_launcher_foreground),
            new Recipe("Recipe 2", R.drawable.ic_launcher_foreground),
            new Recipe("Recipe 3", R.drawable.ic_launcher_foreground),
            new Recipe("Recipe 4", R.drawable.ic_launcher_foreground)
    };

    public List<Recipe> getRecipes() {
        return Arrays.asList(recipes);
    }

    public Recipe getRecipe(String recipeName) {
        for (Recipe recipe: recipes) {
            if (recipe.getName().equals(recipeName)) {
                return recipe;
            }
        }
        return null;
    }
}
