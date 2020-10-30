package com.eshed.fork.Recipe.vm.component;

import com.eshed.fork.Data.model.Direction;

public class DirectionViewModel implements RecipeComponentViewModel, RecipeComponentIsEditable {
    public Direction direction;
    public Boolean isEditable;

    public DirectionViewModel(Direction direction, boolean isEditable) {
        this.direction = direction;
        this.isEditable = isEditable;
    }

    public void changeDirectionText(String directionText) {
        this.direction.setDirectionText(directionText);
    }

    @Override
    public Type getType() {
        return Type.Direction;
    }

    @Override
    public void setIsEditable(boolean isEditable) {
        this.isEditable = isEditable;
    }

    @Override
    public boolean isEditable() {
        return isEditable;
    }
}