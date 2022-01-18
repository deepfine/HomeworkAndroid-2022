package com.example.assignment.retrofit2;

import com.google.gson.annotations.SerializedName;

public class Item {
    @SerializedName("login")
    public String login;

    @SerializedName("id")
    public int id;

    public String getLogin(){
        return login;
    }

    public int getId(){
        return id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setId(int id) {
        this.id = id;
    }
}
