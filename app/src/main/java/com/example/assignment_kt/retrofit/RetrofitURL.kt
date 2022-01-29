package com.example.assignment_kt.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitURL {

    private var retrofitClient: Retrofit? = null

    fun getClient(baseURL: String): Retrofit? {
        if (retrofitClient == null) {

            retrofitClient = Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create()) //gson 컨버트 생성
                .build() //build 이전은 포장
        }
        return retrofitClient
    }
}