package com.example.assignment_kt.recyclerview

import android.content.Intent
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.assignment_kt.R
import com.example.assignment_kt.activity.UserInfoActivity
import com.example.assignment_kt.jsondata.ItemsResponse
import com.example.assignment_kt.util.App
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FavoriteUserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var intent = Intent(itemView.context, UserInfoActivity::class.java)
    private var userListName: TextView = itemView.findViewById(R.id.rv_name)
    private var userFollower: TextView = itemView.findViewById(R.id.follower)
    private var userFollowing: TextView = itemView.findViewById(R.id.following)
    private var rv_id: TextView = itemView.findViewById(R.id.rv_id)
    private var favoriteImg: CheckBox = itemView.findViewById(R.id.favorteList_img)
    private var rv_img: String? = null
    var items = ArrayList<ItemsResponse>()
    val type = object : TypeToken<ArrayList<ItemsResponse>>() {}.type

    fun bind(user: ItemsResponse) {

        userListName.text = user.login
        rv_id.text = " id : ${user.id.toString()}"
        rv_img = user.avatar_url
        Glide.with(itemView)
            .load(rv_img)
            .into(itemView.findViewById(R.id.rv_img))
        favoriteImg.isChecked = user.favorite_boolean
        userFollower.text = "follower : ${user.followers_url}"
        userFollowing.text = "following : ${user.following_url}"


        itemView.setOnClickListener(View.OnClickListener {

            intent.putExtra("userName", user.login)
            intent.putExtra("userId", user.id)
            intent.putExtra("userImage", user.avatar_url)

            itemView.context.startActivity(intent)
        })

        favoriteImg.setOnClickListener(View.OnClickListener {

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