package com.example.konikiewiczb.myapplication.coworkers;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.konikiewiczb.myapplication.R;
import com.example.konikiewiczb.myapplication.model.User;

import java.util.List;

public class WorkersAdapter extends RecyclerView.Adapter<WorkersAdapter.ViewHolder>{

    private List<User> userListResponse;
    private OnItemClickListener mListener;
    private Dialog dialog;
    private Context context;

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

        ViewHolder viewHolder = new ViewHolder(view, mListener);

        dialog = new Dialog(context);
        dialog.setContentView(R.layout.fragment_single_employy);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        viewHolder.item_worker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView dialog_name_tv = (TextView) dialog.findViewById(R.id.tvNameSurname);
                TextView dialog_email_tv = (TextView) dialog.findViewById(R.id.tvEmail);
                dialog_name_tv.setText(userListResponse.get(viewHolder.getAdapterPosition()).toString());
                dialog_email_tv.setText(userListResponse.get(viewHolder.getAdapterPosition()).getEmailAddress());
                Toast.makeText(context,userListResponse.get(viewHolder.getAdapterPosition()).toString(),Toast.LENGTH_SHORT).show();
                dialog.show();
            }
        });

        return viewHolder;
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
        private ConstraintLayout item_worker;
        public TextView tvNameLastname;
        public TextView tvEmail;

        public ViewHolder(View v, OnItemClickListener listener){
            super(v);
            item_worker = (ConstraintLayout) v.findViewById(R.id.worker_item_id);
            tvNameLastname = v.findViewById(R.id.tvNameSurname);
            tvEmail = v.findViewById(R.id.tvEmail);

//            v.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if(listener != null){
//                        int position = getAdapterPosition();
//                        if(position != RecyclerView.NO_POSITION){
//                            listener.onItemClcik(position);
//                        }
//                    }
//                }
//            });
        }
    }

    public WorkersAdapter(List<User> userList, Context context){
        this.userListResponse = userList;
        this.context = context;
    }

}
