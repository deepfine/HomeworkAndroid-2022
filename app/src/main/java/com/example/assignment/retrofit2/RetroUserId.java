package com.example.assignment.retrofit2;

import com.google.gson.annotations.SerializedName;

//Retrofit을 userid로 연결할 경우 body
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
}
