package com.example.assignment_kt.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment_kt.R
import com.example.assignment_kt.jsondata.ItemsResponse
import com.example.assignment_kt.jsondata.UserInfo

class UserListAdapter() : RecyclerView.Adapter<UserViewHolder>() {

    private var userInfoList = ArrayList<ItemsResponse>()
    private val userInfo = ArrayList<UserInfo>()

    //뷰홀더가 생성되었을때
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        // 연결할 레이아웃
        return UserViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.user_item, parent, false))
    }

    //뷰와 뷰홀더가 묶였을때
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(this.userInfoList[position])
    }

    //목록의 아이템 수
    override fun getItemCount(): Int {
        return this.userInfoList.size
    }

    //외부에서 데이터 넘기기
    fun submitList(userInfoList: ArrayList<ItemsResponse>) {
        this.userInfoList = userInfoList
    }
}