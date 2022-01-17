package com.example.assignment.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment.R;
import com.example.assignment.adapter.FavoriteAdapter;

import java.util.ArrayList;

public class Favorite extends Fragment {

    RecyclerView recyclerView2;
    SharedPreferences preferences;
    FavoriteAdapter adapter2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.favorite, container, false);
        recyclerView2 = v.findViewById(R.id.favoriterecyclerview);
        setupRecyclerView();

        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String username = preferences.getString("username", "기본값");
        String userid = preferences.getString("userid", "기본값");

        ArrayList<String> favoritelist = new ArrayList<>();
        favoritelist.add(username);
        favoritelist.add(userid);

        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter2 = new FavoriteAdapter(favoritelist);
        recyclerView2.setAdapter(adapter2);


        return v;
    }

    public void setupRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView2.setLayoutManager(layoutManager);
    }
}
