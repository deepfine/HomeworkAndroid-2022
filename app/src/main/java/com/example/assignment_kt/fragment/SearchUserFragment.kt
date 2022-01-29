package com.example.assignment_kt.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment_kt.R
import com.example.assignment_kt.recyclerview.UserListAdapter
import com.example.assignment_kt.retrofit.RetrofitManager

class SearchUserFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    private lateinit var myRecyclerAdapter: UserListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        val view = inflater.inflate(R.layout.fragment_search_user, container, false)
        val searchUserBtn: Button = view.findViewById(R.id.searchUserBtn)
        val rvProfile: RecyclerView = view.findViewById(R.id.rv_profile)

        //어답터 인스턴스 생성
        myRecyclerAdapter = UserListAdapter()
        //리사이클러뷰 설정
        rvProfile.apply {
            //리사이클러뷰 방향 등 설정
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            // 어답터 장착
            adapter = myRecyclerAdapter
        }

        searchUserBtn.setOnClickListener {

            val searchUser: TextView = view.findViewById(R.id.searchUser)

            RetrofitManager.instance.searchUsers(searchUser.text.toString(),
                completaion = { userList ->
                    if (userList != null) {
                        myRecyclerAdapter.submitList(userList)
                        myRecyclerAdapter.notifyDataSetChanged()
                    }
                })

        }
        return view
    }
}