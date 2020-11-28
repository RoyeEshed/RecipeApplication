package com.eshed.fork.StarredRecipes.vm;

import com.eshed.fork.Data.DbRecipeRepository;
import com.eshed.fork.Data.RecipeRepository;
import com.eshed.fork.Data.model.Recipe;
import com.eshed.fork.Data.model.UserAccount;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class StarredRecipesViewModel {
    private UserAccount user;
    private List<Integer> starredRecipes = new ArrayList<>();
    private RecipeRepository repository;

    public StarredRecipesViewModel(String uid) {
        repository = DbRecipeRepository.getInstance();
        repository.getUserWithUID(uid).subscribe(userAccount -> {
            this.user = userAccount;
            this.starredRecipes = user.getStarredRecipes();
        });

    }

    public Observable<List<StarredRecipeCardViewModel>> getRecipeList() {
        return repository.retrieveRecipes().map(recipes -> {
            List<StarredRecipeCardViewModel> starredList = new ArrayList<>();
            for (Recipe r: recipes) {
                if (starredRecipes.contains((Integer)r.getRecipeID())) {
                    starredList.add(new StarredRecipeCardViewModel(r));
                }
            }
            return starredList;
        });
    }
}