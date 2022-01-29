package com.example.assignment_kt.jsondata

import com.google.gson.annotations.SerializedName

data class ItemsResponse(

    @SerializedName("login")
    var login: String,

    @SerializedName("id")
    var id: Int,

    @SerializedName("avatar_url")
    var avatar_url: String,

    @SerializedName("followers_url")
    var followers_url: String,

    @SerializedName("following_url")
    var following_url: String,

    @SerializedName("starred_url")
    var starred_url: String,

    var favorite_boolean: Boolean = false,

    )