package com.eshed.fork.Browse.vm.browse;

import com.eshed.fork.Recipe.vm.RecipeViewModel;
import com.eshed.fork.data.model.Recipe;
import com.eshed.fork.data.RecipeRepository;

import java.util.ArrayList;
import java.util.List;

public class BrowseViewModel {
    private List<RecipeViewModel> recipeList = new ArrayList<>();
    private RecipeRepository recipeRepository;

    public BrowseViewModel(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;

        List<Recipe> recipes = recipeRepository.getRecipes();
        for (Recipe recipe: recipes) {
            recipeList.add(new RecipeViewModel(recipe.getRecipeID()));
        }
    }

    public List<RecipeViewModel> getRecipeList() {
        return recipeList;
    }
}
