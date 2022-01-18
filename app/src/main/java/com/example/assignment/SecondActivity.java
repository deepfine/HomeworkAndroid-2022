package com.example.assignment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment.adapter.FavoriteAdapter;
import com.example.assignment.retrofit2.RetroUserId;
import com.example.assignment.retrofit2.RetrofitDataService;
import com.example.assignment.retrofit2.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondActivity extends AppCompatActivity {
    TextView textView, favoriteuserlist;
    CheckBox btn_selector;
    RetroUserId retroUserId;
    String username, userid;
    RecyclerView recyclerView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        textView = findViewById(R.id.textView);
        favoriteuserlist = findViewById(R.id.favoriteuserlist);
        btn_selector = findViewById(R.id.btn_selector);
        recyclerView2 = findViewById(R.id.favoriterecyclerview);

        String valuelogin = getIntent().getStringExtra("Login");
        RetrofitDataService service = RetrofitInstance.getRetrofitInstance().create(RetrofitDataService.class);
        Call<RetroUserId> call = service.getUserId(valuelogin);

        call.enqueue(new Callback<RetroUserId>() {
            @Override
            public void onResponse(Call<RetroUserId> call, Response<RetroUserId> response) {
                retroUserId = response.body();
                username = response.body().getLogin();
                userid = response.body().getId();
                textView.setText("username은 " + response.body().getLogin() + ", userid는 " + response.body().getId());
            }

            @Override
            public void onFailure(Call<RetroUserId> call, Throwable t) {

            }
        });

        btn_selector.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = preferences.edit();

                int num=0;

                editor.putString("favoriteUsername" + num ++, username);
                editor.apply();

            }
        });
    }
}