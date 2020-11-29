package com.eshed.fork.Modifications.vm;

import com.eshed.fork.Data.DbRecipeRepository;
import com.eshed.fork.Data.model.Recipe;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class ModificationsViewModel {
    private DbRecipeRepository repository;
    private int parentRecipeID;

    public ModificationsViewModel(DbRecipeRepository repository, int parentRecipeID) {
        this.repository = repository;
        this.parentRecipeID = parentRecipeID;
    }

    public Observable<List<ModificationCardViewModel>> getRecipeList() {
        return repository.retrieveChildrenOfRecipe(parentRecipeID).map(recipes -> {
            List<ModificationCardViewModel> modificationsList = new ArrayList<>();
            for (Recipe r: recipes) {
                modificationsList.add(new ModificationCardViewModel(r));
            }
            return modificationsList;
        });
    }
}