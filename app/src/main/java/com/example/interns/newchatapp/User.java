package com.example.interns.newchatapp;

import java.util.ArrayList;

/**
 * Created by Interns on 4/10/2017.
 */

public class User {

    private String name;
    private String email;

    public User()
    {
    }

    public User(String userName, String eMail) {
        this.name = userName;
        this.email = eMail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
