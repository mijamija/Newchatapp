package com.example.interns.newchatapp;

import java.util.ArrayList;

/**
 * Created by Interns on 4/10/2017.
 */

public class User {

    private String userName;
    private String eMail;
    private ArrayList<String> chatBuddies;

    public User()
    {
    }

    public User(String userName, String eMail) {
        this.userName = userName;
        this.eMail = eMail;
    }

    public void setChatBuddies(ArrayList<String> chatBuddies) { this.chatBuddies = chatBuddies; }

    public ArrayList<String> getChatBuddies() { return chatBuddies; }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
