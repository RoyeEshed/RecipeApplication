package com.eshed.fork.Recipe.view;

import com.eshed.fork.Recipe.vm.component.Footer.FooterViewModel;

public interface FooterCallback {
    void addButtonTapped(FooterViewModel.FooterType type);
}