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
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClcik(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user_coworker, parent, false);

        return new ViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = userListResponse.get(position);
        holder.tvNameLastname.setText(user.toString());
        holder.tvEmail.setText(user.getEmailAddress());
    }

    @Override
    public int getItemCount() {
        return userListResponse.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvNameLastname;
        public TextView tvEmail;

        public ViewHolder(View v, OnItemClickListener listener){
            super(v);
            tvNameLastname = v.findViewById(R.id.tvNameSurname);
            tvEmail = v.findViewById(R.id.tvEmail);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClcik(position);
                        }
                    }
                }
            });
        }
    }

    public WorkersAdapter(List<User> userList){
        this.userListResponse = userList;
    }

}
