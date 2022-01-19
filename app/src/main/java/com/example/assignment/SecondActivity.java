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

                btn_selector.setChecked(searchThisUser());
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

                Item item = new Item();
                item.setId(Integer.parseInt(userid));
                item.setLogin(username);
                List<Item> items = new ArrayList<>();

                //즐겨찾기 버튼 활성화/비활성화 확인
                if (btn_selector.isChecked()) {
                    btn_selector.setChecked(true);

                    //JSON배열에 데이터가 있는 경우
                    if (!originalFavoriteJSON.equals("")) {
                        Type type = new TypeToken<List<Item>>() { //Json String을 List<Item> 객체로 convert
                        }.getType();
                        items = gson.fromJson(originalFavoriteJSON, type); //List<Item>에 담음
                    }

                    items.add(item);
                } else {
                    Type type = new TypeToken<List<Item>>() {
                    }.getType();
                    items = gson.fromJson(originalFavoriteJSON, type);

                    //이미 즐겨찾기 목록에 있는 경우, 즐겨찾기 버튼 '비활성화'로 변경 시 List에서 제거
                    btn_selector.setChecked(false);
                    if (searchThisUser()) {
                        for (int i=0; i<items.size(); i++) {
                            if (username.equals(items.get(i).login)) {
                                items.remove(i);
                            }
                        }
                    }
                }

                String favoriteJSON = gson.toJson(items);
                saveFavorite(favoriteJSON);
            }
        });
    }

    ////sharedpreference로 Get
    private String getFavoriteJSON() {
        String favoriteJSON = com.example.assignment.preference.PreferenceManager.getString(this.getApplicationContext(), "favoriteJSON");
        return favoriteJSON;
    }


    //sharedpreference로 저장
    private void saveFavorite(String favoriteJSON) {
        com.example.assignment.preference.PreferenceManager.setString(
                getApplicationContext(),
                "favoriteJSON",
                favoriteJSON
        );
    }

    //즐겨찾기 활성화/비활성화 return
    private boolean searchThisUser() {
        //로그인 아이디가 sharedprference에 있으면 true, 없으면 false
        boolean isExist = false;
        String originalFavoriteJSON = getFavoriteJSON();
        if (originalFavoriteJSON.equals(""))
            return false;

        Type type = new TypeToken<List<Item>>() {}.getType();
        List<Item> items = gson.fromJson(originalFavoriteJSON, type);
        for (int i=0; i<items.size(); i++) {
            if (username.equals(items.get(i).login)) {
                isExist = true;
            }
        }

        return isExist;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}