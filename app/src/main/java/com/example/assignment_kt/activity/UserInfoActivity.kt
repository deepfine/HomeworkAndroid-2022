package com.example.assignment_kt.activity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.assignment_kt.R
import com.example.assignment_kt.retrofit.RetrofitManager

class UserInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstaceState: Bundle?) {
        super.onCreate(savedInstaceState)
        setContentView(R.layout.user_info)

        val infoName : TextView = findViewById(R.id.info_name)
        val infoId : TextView = findViewById(R.id.info_id)

        val userName = intent.getStringExtra("userName") ?: ""

        RetrofitManager.instance.getUserInfo(userName, completaion = { userList ->
            if (userList != null) {
                infoName.text = userList.login
                infoId.text = userList.id.toString()
                Glide.with(this)
                    .load(userList.avatar_url)
                    .into(findViewById(R.id.user_img))
            }
        })
    }
}