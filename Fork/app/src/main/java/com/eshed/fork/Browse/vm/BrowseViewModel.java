package com.eshed.fork.Browse.vm;

import android.util.Log;

import com.eshed.fork.Data.DbRecipeRepository;
import com.eshed.fork.Data.RecipeRepository;
import com.eshed.fork.Data.model.Recipe;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class BrowseViewModel  {
    private RecipeRepository recipeRepository;

    public BrowseViewModel() {
        recipeRepository = DbRecipeRepository.getInstance();
    }

    public Observable<List<RecipeCardViewModel>> getRecipeList() {
        return recipeRepository.retrieveRecipes().map(recipes -> {
            List<RecipeCardViewModel> recipesList = new ArrayList<>();
            for (Recipe r: recipes) {
                int numChildren = recipeRepository.numberOfChildren(r);
                if (r.getParentRecipeID() == -1) {
                    recipesList.add(new RecipeCardViewModel(r, numChildren));
                }
            }
            Log.d("TAG", "getRecipeList: ***************************************************************");
            return recipesList;
        });
    }
}
