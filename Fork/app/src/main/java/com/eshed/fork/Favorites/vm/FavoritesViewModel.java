package com.eshed.fork.Favorites.vm;

import com.eshed.fork.Data.DbRecipeRepository;
import com.eshed.fork.Data.RecipeRepository;
import com.eshed.fork.Data.model.Recipe;
import com.eshed.fork.Data.model.UserAccount;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class FavoritesViewModel  {
    private UserAccount user;
    private List<Integer> favoritedRecipes = new ArrayList<>();
    private RecipeRepository repository;

    public FavoritesViewModel(String uid) {
        repository = DbRecipeRepository.getInstance();
        repository.getUserWithUID(uid).subscribe(userAccount -> {
            this.user = userAccount;
            this.favoritedRecipes = user.getFavoritedRecipes();
        });

    }

    public Observable<List<FavoritesCardViewModel>> getRecipeList() {
        return repository.retrieveRecipes().map(recipes -> {
            List<FavoritesCardViewModel> favoritesList = new ArrayList<>();
            for (Recipe r: recipes) {
                if (favoritedRecipes.contains((Integer)r.getRecipeID())) {
                    favoritesList.add(new FavoritesCardViewModel(r));
                }
            }
            return favoritesList;
        });
    }
}