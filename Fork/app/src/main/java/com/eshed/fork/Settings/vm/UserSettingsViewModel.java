package com.eshed.fork.Settings.vm;

import android.util.Log;

import com.eshed.fork.Browse.vm.RecipeCardViewModel;
import com.eshed.fork.Data.DbRecipeRepository;
import com.eshed.fork.Data.model.Recipe;
import com.eshed.fork.Data.model.UserAccount;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.disposables.Disposable;

public class UserSettingsViewModel {
    public UserAccount user;
    private Disposable disposable;
    private DbRecipeRepository repository;

    public UserSettingsViewModel(String userUid, DbRecipeRepository repository) {
        this.repository = repository;
        disposable = repository
                .getUserWithUID(userUid)
                .subscribe(user -> {
                    this.user = user;
                });
    }

    public void saveUserImage(String imageURL) {
        user.setImageURL(imageURL);
        repository.saveUser(user);
    }

}
