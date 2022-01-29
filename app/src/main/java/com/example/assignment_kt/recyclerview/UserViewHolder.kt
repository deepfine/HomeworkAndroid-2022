package com.example.assignment_kt.recyclerview

import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.assignment_kt.R
import com.example.assignment_kt.activity.UserInfoActivity
import com.example.assignment_kt.jsondata.ItemsResponse
import com.example.assignment_kt.jsondata.UserInfo
import com.example.assignment_kt.retrofit.RetrofitManager
import com.example.assignment_kt.util.App
import com.example.assignment_kt.util.Constants.TAG
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var favoriteUserName: TextView = itemView.findViewById(R.id.rv_name)
    private var rv_id: TextView = itemView.findViewById(R.id.rv_id)
    private var userListCheck: CheckBox = itemView.findViewById(R.id.userList_check)
    private var rv_img: String? = null
    private var intent = Intent(itemView.context, UserInfoActivity::class.java)
    var items = ArrayList<ItemsResponse>()
    val type = object : TypeToken<ArrayList<ItemsResponse>>() {}.type
    private var userFollower: TextView = itemView.findViewById(R.id.follower)
    private var userFollowing: TextView = itemView.findViewById(R.id.following)


    fun bind(user: ItemsResponse) {

        favoriteUserName.text = user.login
        rv_id.text = user.id.toString()
        rv_img = user.avatar_url
        userListCheck.isChecked = user.favorite_boolean
//        userFollower.text = "follower : ${userInfo.login}"
//        userFollowing.text = "following : ${userInfo().login}"

        //이미지
        Glide.with(itemView)
            .load(rv_img)
            .into(itemView.findViewById(R.id.rv_img))

        itemView.setOnClickListener(View.OnClickListener {

            intent.putExtra("userName", user.login)
            intent.putExtra("userId", user.id)
            intent.putExtra("userImage", user.avatar_url)

            itemView.context.startActivity(intent)
        })

        userListCheck.setOnClickListener(View.OnClickListener {

            if (!user.favorite_boolean) {

                user.favorite_boolean = true

                if (App.prefs.favoriteJSON == null) {
                    App.prefs.favoriteJSON = Gson().toJson(arrayListOf(user))
                } else if (items.isEmpty()) {
                    items = Gson().fromJson(App.prefs.favoriteJSON, type)
                    items.add(user)
                    App.prefs.favoriteJSON = Gson().toJson(items)
                } else {
                    items = Gson().fromJson(App.prefs.favoriteJSON, type)
                    items.add(user)
                    App.prefs.favoriteJSON = Gson().toJson(items)
                }
            } else {
                user.favorite_boolean = false

                val type = object : TypeToken<ArrayList<ItemsResponse>>() {}.type
                items = Gson().fromJson(App.prefs.favoriteJSON, type)
                items.remove(items.find { it.login == user.login })
                App.prefs.favoriteJSON = Gson().toJson(items)
            }
        })
    }
}