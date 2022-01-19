package com.example.assignment.retrofit2;

import android.graphics.Bitmap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//연결한 깃허브의 body 정보
public class Item {
    @SerializedName("login")
    public String login;

    @SerializedName("id")
    public int id;

    public String getLogin() {
        return login;
    }

    public int getId() {
        return id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setId(int id) {
        this.id = id;
    }
}
