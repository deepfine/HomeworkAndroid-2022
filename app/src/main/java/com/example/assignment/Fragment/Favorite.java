package com.example.assignment.Fragment;

import android.os.Bundle;
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
import com.example.assignment.retrofit2.Item;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Favorite extends Fragment {
    TextView favoriteuserlist;
    RecyclerView favoriterecyclerview;
    String favoriteUsername;
    String contact_person;
    Gson gson = new Gson();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.favorite, container, false);
        favoriterecyclerview = v.findViewById(R.id.favoriterecyclerview);
        favoriteuserlist = v.findViewById(R.id.favoriteuserlist);
        favoriterecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));

        Type type = new TypeToken<List<Item>>() {}.getType();
        List<Item> items = gson.fromJson(getFavoriteJSON(), type);

        FavoriteAdapter adapter = new FavoriteAdapter(items);
        favoriterecyclerview.setAdapter(adapter);

        return v;
    }

    private String getFavoriteJSON() {
        String userId = com.example.assignment.preference.PreferenceManager.getString(getContext(), "favoriteJSON");
        if (userId.equals("")) {
            userId = "저장된 데이터가 없습니다.";
        }
        return userId;
    }

//    private ArrayList<String> ReadFriendsData() {
//        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getContext());
//        Gson gson = new Gson();
//        String json = pref.getString("MyFriends", "EMPTY");
//        Type type = new TypeToken<ArrayList<String>>(){}.getType();
//        ArrayList<String> arrayList = gson.fromJson(json, type);
//        return arrayList;
//    }
}
