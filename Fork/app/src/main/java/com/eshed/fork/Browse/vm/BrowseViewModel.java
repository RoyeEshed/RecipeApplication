package com.eshed.fork.Browse.vm;

import androidx.lifecycle.ViewModel;

import com.eshed.fork.Recipe;
import com.eshed.fork.data.RecipeRepository;

import java.util.ArrayList;
import java.util.List;

public class BrowseViewModel extends ViewModel {
    private List<RecipeViewModel> recipeList = new ArrayList<>();
    private RecipeRepository recipeRepository;

    public BrowseViewModel(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;

        List<Recipe> recipes = recipeRepository.getRecipes();
        for (Recipe recipe: recipes) {
            recipeList.add(new RecipeViewModel(recipe));
        }
    }

    public List<RecipeViewModel> getRecipeList() {
        return recipeList;
    }
}
