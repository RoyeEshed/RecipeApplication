package com.eshed.fork.Recipe.view;

import com.eshed.fork.Recipe.vm.component.RecipeComponentViewModel;

public interface FooterCallback {
    void addButtonTapped(RecipeComponentViewModel.Type type);
    void cancelButtonTapped();
}