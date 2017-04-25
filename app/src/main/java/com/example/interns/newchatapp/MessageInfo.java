package com.example.interns.newchatapp;

/**
 * Created by Interns on 4/19/2017.
 */

public class MessageInfo {

    private String text;
    private String fromId;
    private String told;
    private String timestamp;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getTold() {
        return told;
    }

    public void setTold(String told) {
        this.told = told;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
