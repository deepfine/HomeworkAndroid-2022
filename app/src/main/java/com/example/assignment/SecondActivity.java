package com.example.assignment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment.preference.PreferenceManager;
import com.example.assignment.retrofit2.Item;
import com.example.assignment.retrofit2.RetroUserId;
import com.example.assignment.retrofit2.RetrofitDataService;
import com.example.assignment.retrofit2.RetrofitInstance;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
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
    Gson gson;

    @SuppressLint("ResourceType")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        textView = findViewById(R.id.textView);
        favoriteuserlist = findViewById(R.id.favoriteuserlist);
        btn_selector = findViewById(R.id.btn_selector);
        recyclerView2 = findViewById(R.id.favoriterecyclerview);

        String getuser = getIntent().getStringExtra("Login");
        RetrofitDataService service = RetrofitInstance.getRetrofitInstance().create(RetrofitDataService.class);
        Call<RetroUserId> call = service.getUserId(getuser);
        gson = new GsonBuilder().create();

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
                Gson gson = new Gson();
                String originalFavoriteJSON = getFavoriteJSON();

                if (!btn_selector.isChecked() == false) {
                    btn_selector.setChecked(true);

                    if (originalFavoriteJSON.equals("")) {
                        Item item = new Item();
                        item.setId(Integer.parseInt(userid));
                        item.setLogin(username);

                        List<Item> items = new ArrayList<>();
                        items.add(item);
                        String favoriteJSON = gson.toJson(items);
                        com.example.assignment.preference.PreferenceManager.setString(
                                getApplicationContext(),
                                "favoriteJSON",
                                favoriteJSON
                        );

                    } else {
                        Item item = new Item();
                        item.setId(Integer.parseInt(userid));
                        item.setLogin(username);

                        Type type = new TypeToken<List<Item>>() {
                        }.getType();
                        List<Item> items = gson.fromJson(originalFavoriteJSON, type);
                        items.add(item);
                        String newFavoriteJSON = gson.toJson(items);
                        com.example.assignment.preference.PreferenceManager.setString(
                                getApplicationContext(),
                                "favoriteJSON",
                                newFavoriteJSON
                        );
                    }
                } else {
                    btn_selector.setChecked(false);
                }
            }
        });
    }

    private String getFavoriteJSON() {
        String favoriteJSON = com.example.assignment.preference.PreferenceManager.getString(this.getApplicationContext(), "favoriteJSON");
        return favoriteJSON;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
//
//    public void setCheckedBoolean() {
//        com.example.assignment.preference.PreferenceManager.getBoolean(
//                SecondActivity.this, "boolean" + username);
//    }
}