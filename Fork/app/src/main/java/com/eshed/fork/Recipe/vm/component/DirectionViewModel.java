package com.eshed.fork.Recipe.vm.component;

import com.eshed.fork.data.model.Direction;

public class DirectionViewModel implements RecipeComponentViewModel, RecipeComponentIsEditable {
    public Direction direction;
    public Boolean isEditable;

    public DirectionViewModel(Direction direction, Boolean isEditable) {
        this.direction = direction;
        this.isEditable = isEditable;
    }

    @Override
    public Type getType() {
        return Type.Direction;
    }

    @Override
    public void setIsEditable(Boolean isEditable) {
        this.isEditable = isEditable;
    }

    @Override
    public Boolean isEditable() {
        return isEditable;
    }
}