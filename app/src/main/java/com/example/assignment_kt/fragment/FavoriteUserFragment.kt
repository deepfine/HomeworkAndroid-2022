package com.example.assignment_kt.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment_kt.R
import com.example.assignment_kt.jsondata.ItemsResponse
import com.example.assignment_kt.recyclerview.FavoriteListAdapter
import com.example.assignment_kt.util.App
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FavoriteUserFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private lateinit var favoriteListAdapter: FavoriteListAdapter
    var items = ArrayList<ItemsResponse>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorite_user, container, false)
        val rvFavorite: RecyclerView = view.findViewById(R.id.rv_favorite)

        favoriteListAdapter = FavoriteListAdapter()

        val type = object : TypeToken<ArrayList<ItemsResponse>>() {}.type
        items = Gson().fromJson(App.prefs.favoriteJSON, type) ?: ArrayList<ItemsResponse>()

        favoriteListAdapter.submitList(items)

        favoriteListAdapter.notifyDataSetChanged()


        rvFavorite.apply {
            //리사이클러뷰 방향 등 설정

            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            // 어답터 장착
            adapter = favoriteListAdapter


        }

        return view
    }
}