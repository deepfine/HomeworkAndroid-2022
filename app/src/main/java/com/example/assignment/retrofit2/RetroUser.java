package com.example.assignment.retrofit2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RetroUser {
    @Expose
    @SerializedName("items")
    List<Item> items;

    public List<Item> getItems(){
        return items;
    }

    public class Item{
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
    }
}
