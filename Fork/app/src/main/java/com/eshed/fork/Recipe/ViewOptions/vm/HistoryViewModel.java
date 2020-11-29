package com.eshed.fork.Recipe.ViewOptions.vm;

import com.eshed.fork.Data.DbRecipeRepository;
import com.eshed.fork.Data.model.Recipe;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;


public class HistoryViewModel {
    private DbRecipeRepository repository;
    private int recipeID;
    private List<AncestorCardViewModel> ancestors = new ArrayList<>();
    private List<Recipe> recipeList;
    private Recipe mainRecipe;

    public HistoryViewModel(DbRecipeRepository repository, int recipeID) {
        this.repository = repository;
        this.recipeID = recipeID;
        this.recipeList = new ArrayList<>();

        repository.retrieveRecipes().subscribe(recipes -> {
            this.recipeList = recipes;
        });

        for (Recipe r: recipeList) {
            if (r.getRecipeID() == recipeID) {
                this.mainRecipe = r;
                break;
            }
        }
    }

    public Observable<List<AncestorCardViewModel>> getAncestors() {
        int index = recipeID;

        while (index > 0) {
            Recipe r = getRecipe(index);
            ancestors.add(new AncestorCardViewModel(r));
            index = r.getParentRecipeID();
        }

        // reverse list
        for (int i = 0; i < ancestors.size() / 2; i++) {
            AncestorCardViewModel temp = ancestors.get(i);
            ancestors.set(i, ancestors.get(ancestors.size() - i - 1));
            ancestors.set(ancestors.size() - i - 1, temp);
        }

        return Observable.just(ancestors);
    }

    private Recipe getRecipe(int id) {
        for (Recipe r: recipeList) {
            if (r.getRecipeID() == id) {
                return r;
            }
        }
        return null;
    }

}
