package com.eshed.fork.data.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String email;
    private List<String> dietaryRestrictions;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
        this.dietaryRestrictions = new ArrayList<>();
    }

    public void setDietaryRestrictions(List<String> dietaryRestrictions) {
        this.dietaryRestrictions = dietaryRestrictions;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getDietaryRestrictions() {
        return dietaryRestrictions;
    }
}
