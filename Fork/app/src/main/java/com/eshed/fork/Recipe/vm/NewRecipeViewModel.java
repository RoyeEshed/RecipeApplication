package com.eshed.fork.Recipe.vm;

import com.eshed.fork.Browse.vm.RecipeViewModel;
import com.eshed.fork.R;
import com.eshed.fork.data.RecipeRepository;
import com.eshed.fork.data.model.Ingredient;
import com.eshed.fork.data.model.Recipe;

import java.util.List;

public class NewRecipeViewModel {
    private RecipeRepository recipeRepository;

    public NewRecipeViewModel(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public void onNewRecipeCreated(String name, int imageResource, String contributor, List<Ingredient> ingredients,
                                   List<String> directions, List<String> tags) {
        int id = recipeRepository.getSize() + 1;
        Recipe recipe;

        if (imageResource == -1) {
            recipe = new Recipe(id, name, R.drawable.ic_launcher_foreground, contributor, ingredients,directions, tags);
        } else {
            recipe = new Recipe(id, name, imageResource, contributor, ingredients,directions, tags);
        }
        recipeRepository.addRecipe(recipe);
    }
}
