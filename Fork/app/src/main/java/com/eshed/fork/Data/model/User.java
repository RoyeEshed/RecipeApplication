package com.eshed.fork.Data.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String email;
    private String uid;

    public User(String email, String uid) {
        this.email = email;
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }
}
