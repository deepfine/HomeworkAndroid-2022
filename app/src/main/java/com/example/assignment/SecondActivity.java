package com.example.assignment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment.retrofit2.RetroUserId;
import com.example.assignment.retrofit2.RetrofitDataService;
import com.example.assignment.retrofit2.RetrofitInstance;

import java.util.HashSet;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondActivity extends AppCompatActivity {

    TextView textView;
    CheckBox btn_selector;
    public SharedPreferences preferences;
    RetroUserId retroUserId;
    String username, userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        textView = findViewById(R.id.textView);
        btn_selector = findViewById(R.id.btn_selector);
        preferences = getSharedPreferences("UserInfo", MODE_PRIVATE);
        //모드 => MODE_PRIVATE (이 앱에서만 사용가능)

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

                editor.putString("username", username);
                editor.putString("userid", userid);
                editor.apply();

            }
        });
    }
}