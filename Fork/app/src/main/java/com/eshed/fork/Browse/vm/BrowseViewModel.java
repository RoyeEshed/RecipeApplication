package com.eshed.fork.Browse.vm;

import com.eshed.fork.data.DebugRecipeRepository;
import com.eshed.fork.data.RecipeRepository;
import com.eshed.fork.data.model.Recipe;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class BrowseViewModel {
    private RecipeRepository recipeRepository = DebugRecipeRepository.getInstance();

    public Observable<List<RecipeCardViewModel>> getRecipeList() {
        return recipeRepository.getRecipes().map(recipes -> {
            List<RecipeCardViewModel> recipesList = new ArrayList<>();
            for (Recipe r: recipes) {
                recipesList.add(new RecipeCardViewModel(r));
            }
            return recipesList;
        });
    }
}
