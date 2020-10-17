package com.eshed.fork.data.model;

public class Direction {
    int directionNumber;
    String directionText;
    int recipeID;

    public Direction(int directionNumber, String directionText, int recipeID) {
        this.directionNumber = directionNumber;
        this.directionText = directionText;
        this.recipeID = recipeID;
    }

    // temp
    public Direction(int directionNumber, String directionText) {
        this(directionNumber, directionText, -1);
    }

    public int getDirectionNumber() {
        return directionNumber;
    }

    public String getDirectionText() {
        return directionText;
    }
}
