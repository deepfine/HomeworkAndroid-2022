package com.example.assignment_kt.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment_kt.R
import com.example.assignment_kt.jsondata.ItemsResponse

class FavoriteListAdapter : RecyclerView.Adapter<FavoriteUserViewHolder>() {

    private var favoriteList = ArrayList<ItemsResponse>()


    //viewholder create & linked layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteUserViewHolder {
        return FavoriteUserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.favorite_item,parent,false))
    }

    //view & viewhodler bind
    override fun onBindViewHolder(holder: FavoriteUserViewHolder, position: Int) {
        holder.bind(favoriteList[position])
    }

    //itemsize
    override fun getItemCount(): Int {
        return favoriteList.size
    }

    fun submitList(favoriteList : ArrayList<ItemsResponse>){
        this.favoriteList = favoriteList
    }
}