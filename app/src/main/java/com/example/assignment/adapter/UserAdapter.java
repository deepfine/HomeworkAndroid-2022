package com.example.assignment.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment.R;
import com.example.assignment.SecondActivity;
import com.example.assignment.retrofit2.RetroUser;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private RetroUser retroUser;
    CheckBox btn_selector;
    ImageView userImage;

    public UserAdapter(RetroUser dataList) {
        this.retroUser = dataList;
    }

    class UserViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;

        UserViewHolder(View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.title);
            btn_selector = itemView.findViewById(R.id.btn_selector);
            userImage = itemView.findViewById(R.id.userImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), SecondActivity.class);
                    int pos = getAdapterPosition();

                    //Intent로 Layout이동 시 값을 전달함
                    intent.putExtra("Login", retroUser.getItems().get(pos).login);
                    intent.putExtra("Id", retroUser.getItems().get(pos).id);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.userdetail, parent, false);

        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.txtTitle.setText(retroUser.getItems().get(position).login + "\n" +
                retroUser.getItems().get(position).id);
    }

    // size of array
    @Override
    public int getItemCount() {
        return retroUser.getItems().size();
    }
}