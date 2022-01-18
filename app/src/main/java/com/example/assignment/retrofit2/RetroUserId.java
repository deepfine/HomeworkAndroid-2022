package com.example.assignment.retrofit2;

import com.google.gson.annotations.SerializedName;

import java.nio.file.attribute.UserDefinedFileAttributeView;

public class RetroUserId {
    @SerializedName("login")
    private String login;
    private String id;

    public RetroUserId(String title) {
        this.login = title;
    }

    public String getLogin() {
        return login;
    }

    public String getId() {
        return id;
    }

    public String getfollowers() {
        return login;
    }

    public String getfollowing() {
        return login;
    }

    public String getEmail() {
        return login;
    }
}
