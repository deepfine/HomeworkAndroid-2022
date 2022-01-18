package com.example.assignment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment.retrofit2.Item;
import com.example.assignment.retrofit2.RetroUser;
import com.example.assignment.retrofit2.RetroUserId;
import com.example.assignment.retrofit2.RetrofitDataService;
import com.example.assignment.retrofit2.RetrofitInstance;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    String username, userid, contact_user;
    RecyclerView recyclerView2;
    Gson gson;
    ArrayList<String> users;


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
//                    try {
//                        JSONArray jsonArray = new JSONArray(originalFavoriteJSON);
//                        List<Item> items = new ArrayList<>();
//                        items = gson.fromJson(originalFavoriteJSON, Item.class);
//
                    Item item = new Item();
                    item.setId(Integer.parseInt(userid));
                    item.setLogin(username);
//
//                        JSONObject jsonObject = new JSONObject();
//                        jsonObject.put("id", Integer.parseInt(userid));
//                        jsonObject.put("login", username);
//                        jsonArray.put(jsonObject);
//
//                        Log.d("yjc", "22: " +gson.toJson(jsonArray));
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
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
            }
        });
    }

    private String getFavoriteJSON() {
        String favoriteJSON = com.example.assignment.preference.PreferenceManager.getString(this.getApplicationContext(), "favoriteJSON");
        return favoriteJSON;
    }
}