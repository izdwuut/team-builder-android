package com.example.konikiewiczb.myapplication.coworkers.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.konikiewiczb.myapplication.R;
import com.example.konikiewiczb.myapplication.framework.adapter.GenericAdapter;
import com.example.konikiewiczb.myapplication.model.User;

public class WorkersAdapter extends GenericAdapter<User, WorkersViewHolder> {
    public WorkersAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public WorkersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user_coworker, parent, false);

        return new WorkersViewHolder(view, context, dataset);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkersViewHolder holder, int position) {
        User user = dataset.get(position);
        holder.tvNameLastname.setText(user.toString());
        holder.tvEmail.setText(user.getEmailAddress());
    }
}
