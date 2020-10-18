package com.eshed.fork.Recipe.vm.component.Footer;

import com.eshed.fork.Recipe.vm.component.RecipeComponentViewModel;

public interface FooterViewModel {
    enum FooterType {
        Direction_Footer,
        Ingredient_Footer,
    }
     RecipeComponentViewModel.Type getType();
     FooterType getFooterType();
     void setIsEditable(Boolean isEditable);
     Boolean isEditable();
}
