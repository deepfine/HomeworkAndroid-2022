package com.example.assignment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.assignment.R;
import com.example.assignment.retrofit2.Item;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {

    private List<Item> mData;

    public FavoriteAdapter(List<Item> data) {
        this.mData = data;
    }

    //ViewHolder에 View 저장
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView favoriteuserlist;

        public ViewHolder(View view) {
            super(view);
            favoriteuserlist = itemView.findViewById(R.id.favoriteuserlist);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Layout 을 객체화
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.favoritedetail, parent, false);
        return new ViewHolder(view);
    }

    //ViewHolder에 Data Binding
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String favoriteUser = mData.get(position).login;
        holder.favoriteuserlist.setText(favoriteUser);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
