package com.example.assignment.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment.R;
import com.example.assignment.adapter.FavoriteAdapter;

import java.util.ArrayList;

public class Favorite extends Fragment {
    TextView favoriteuserlist;
    RecyclerView favoriterecyclerview;
    String favoriteUsername;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.favorite, container, false);
        favoriterecyclerview = v.findViewById(R.id.favoriterecyclerview);
        favoriteuserlist = v.findViewById(R.id.favoriteuserlist);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        favoriterecyclerview.setLayoutManager(layoutManager);

        //adapter를 디바이스 저장소에서 받아옴
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = preferences.edit();
        favoriterecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<String> userlist = new ArrayList<String>();


        favoriteUsername = preferences.getString("favoriteUsername" , "기본값");
        userlist.add(favoriteUsername);

        favoriterecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        FavoriteAdapter adapter = new FavoriteAdapter(userlist);
        favoriterecyclerview.setAdapter(adapter);

        return v;
    }
}
