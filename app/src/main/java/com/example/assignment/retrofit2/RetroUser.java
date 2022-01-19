package com.example.assignment.retrofit2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


//Json Array > items 오브젝트
public class RetroUser {
    @Expose
    @SerializedName("items")
    List<Item> items;

    public List<Item> getItems() {
        return items;
    }
}
