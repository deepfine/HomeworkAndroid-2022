package com.example.assignment;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.assignment.Fragment.Favorite;
import com.example.assignment.Fragment.UserSearch;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private UserSearch userSearch = new UserSearch();
    private Favorite favorite = new Favorite();
    private FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTransaction transaction = fragmentManager.beginTransaction(); //2번 써야하는가?
        transaction.replace(R.id.content_layout, userSearch).commit();

        Log.d("변화","gd");

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_view);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                switch (item.getItemId()) {
                    case R.id.navigation_1:
                        transaction.replace(R.id.content_layout, userSearch).commit();
                        break;
                    case R.id.navigation_2:
                        transaction.replace(R.id.content_layout, favorite).commit();
                        break;
                }
                return true;
            }
        });
    }
}