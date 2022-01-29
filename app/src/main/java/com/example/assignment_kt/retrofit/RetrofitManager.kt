package com.example.assignment_kt.retrofit

import android.util.Log
import com.example.assignment_kt.jsondata.SearchUsers
import com.example.assignment_kt.jsondata.ItemsResponse
import com.example.assignment_kt.jsondata.UserInfo
import com.example.assignment_kt.util.API
import com.example.assignment_kt.util.App
import com.example.assignment_kt.util.Constants.TAG
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Response

class RetrofitManager {

    //싱글턴
    companion object { //instance 생성 없이 클래스명 다음 (.)을 사용하여 참조
        val instance = RetrofitManager()
    }

    //레트로핏 인터페이스 가져오기, ?=기본값, ?.리턴값
    private val linkedRetrofit: LinkedRetrofit? =
        RetrofitURL.getClient(API.BASE_URL)?.create(LinkedRetrofit::class.java)

    fun searchUsers(
        searchTerm: String?,
        completaion: (ArrayList<ItemsResponse>?) -> Unit,
    ) {

        val term = searchTerm ?: "" //default ""
        val call = linkedRetrofit?.searchUsers(searchTerm = term) ?: return

        call.enqueue(object : retrofit2.Callback<SearchUsers> {
            override fun onResponse(call: Call<SearchUsers>, response: Response<SearchUsers>) {

                val searchUsers: SearchUsers? = response.body()
                val userLogin = searchUsers?.items

                val type = object : TypeToken<ArrayList<ItemsResponse>>() {}.type
                val favorites =
                    Gson().fromJson(App.prefs.favoriteJSON, type) ?: ArrayList<ItemsResponse>()

                userLogin?.forEach { user ->
                    favorites.forEach { favorite ->
                        if (favorite.login == user.login) {
                            user.favorite_boolean = true
                        }
                    }
                }

                completaion(userLogin)
            }

            override fun onFailure(call: Call<SearchUsers>, t: Throwable) {
                Log.d(TAG, "onFailure: 응답실패 : ${t.toString()}")
            }
        })
    }

    fun getUserInfo(
        userName: String,
        completaion: (UserInfo?) -> Unit,
    ) {
        val call = linkedRetrofit?.getUserInfo(userName) ?: return

        call.enqueue(object : retrofit2.Callback<UserInfo> {
            override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {

                val resultInfo = response.body()
                completaion(resultInfo)
            }

            override fun onFailure(call: Call<UserInfo>, t: Throwable) {
                Log.d(TAG, "onFailure: 응답실패 : ${t.toString()}")
            }
        })
    }
}
