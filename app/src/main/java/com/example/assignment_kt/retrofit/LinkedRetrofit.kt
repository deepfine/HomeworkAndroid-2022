package com.example.assignment_kt.retrofit

import com.example.assignment_kt.jsondata.SearchUsers
import com.example.assignment_kt.jsondata.UserInfo
import com.example.assignment_kt.util.API
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LinkedRetrofit {

    @GET(API.SEARCH_USERS)
    fun searchUsers(@Query("q") searchTerm: String): Call<SearchUsers>

    @GET("users/{user}")
    fun getUserInfo(@Path("user") user: String): Call<UserInfo>

}