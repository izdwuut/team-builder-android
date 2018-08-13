package com.example.konikiewiczb.myapplication.coworkers;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.konikiewiczb.myapplication.R;
import com.example.konikiewiczb.myapplication.model.User;

import java.util.List;

public class WorkersAdapter extends RecyclerView.Adapter<WorkersAdapter.ViewHolder>{

    List<User> userListResponse;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user_coworker, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = userListResponse.get(position);
        holder.tvNameLastname.setText(user.getFirstname() + " " + user.getLastname());
        holder.tvEmail.setText(user.getUserEmail());
    }

    @Override
    public int getItemCount() {
        return userListResponse.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvNameLastname;
        public TextView tvEmail;

        public ViewHolder(View v){
            super(v);
            tvNameLastname = v.findViewById(R.id.tvNameSurname);
            tvEmail = v.findViewById(R.id.tvEmail);
        }
    }

    public WorkersAdapter(List<User> userList){
        this.userListResponse = userList;
    }

}
