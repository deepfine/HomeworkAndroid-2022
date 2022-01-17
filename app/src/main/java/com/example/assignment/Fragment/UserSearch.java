package com.example.assignment.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment.R;
import com.example.assignment.adapter.UserAdapter;
import com.example.assignment.retrofit2.RetroUser;
import com.example.assignment.retrofit2.RetrofitDataService;
import com.example.assignment.retrofit2.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserSearch extends Fragment {

    private RecyclerView recyclerView;
    public UserAdapter adapter;
    EditText search_id;
    Button search;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.usersearch, container, false);
        recyclerView = view.findViewById(R.id.customRecyclerView);
        search_id = view.findViewById(R.id.search_id);
        search = view.findViewById(R.id.search);
        setupRecyclerView();

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrofitDataService service = RetrofitInstance.getRetrofitInstance().create(RetrofitDataService.class);
                Call<RetroUser> call = service.getUser(search_id.getText().toString());
                call.enqueue(new Callback<RetroUser>() {

                    @Override
                    public void onResponse(Call<RetroUser> call, Response<RetroUser> response) {
                        Toast.makeText(getContext(), "통신성공", Toast.LENGTH_SHORT).show();
                        RetroUser userList = response.body();
                        onSucceed(userList);
                    }

                    @Override
                    public void onFailure(Call<RetroUser> call, Throwable t) {
                        Toast.makeText(getContext(), "통신실패", Toast.LENGTH_SHORT).show();
                    }
                });
                recyclerView.setVisibility(View.VISIBLE);
            }
        });
        return view;
    }

    public void setupRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
    }

    private void onSucceed(RetroUser response) {
        adapter = new UserAdapter(response);
        recyclerView.setAdapter(adapter);
    }
}
