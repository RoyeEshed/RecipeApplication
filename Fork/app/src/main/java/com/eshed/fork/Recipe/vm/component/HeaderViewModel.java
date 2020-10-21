package com.eshed.fork.Recipe.vm.component;

public class HeaderViewModel implements RecipeComponentViewModel {
    public String header;

    public HeaderViewModel(String header) {
        this.header = header;
    }

    @Override
    public Type getType() {
        return Type.Header;
    }
}
