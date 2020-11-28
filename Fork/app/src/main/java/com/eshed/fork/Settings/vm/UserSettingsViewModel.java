package com.eshed.fork.Settings.vm;

import com.eshed.fork.Data.DbRecipeRepository;
import com.eshed.fork.Data.model.UserAccount;

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
