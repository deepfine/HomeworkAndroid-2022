package com.example.assignment.retrofit2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

//baseUrl 이후 연결 주소
public interface RetrofitDataService {
    @GET("/search/users")
    Call<RetroUser> getUser(@Query("q") String query);

    @GET("users/{user}")
    Call<RetroUserId> getUserId(@Path("user") String user);
}
