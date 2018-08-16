package com.example.konikiewiczb.myapplication.projects.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.konikiewiczb.myapplication.R;
import com.example.konikiewiczb.myapplication.coworkers.WorkersAdapter;
import com.example.konikiewiczb.myapplication.framework.views.IconTextView;
import com.example.konikiewiczb.myapplication.model.UserProject;

import java.util.List;

public class UserProjectsAdapter extends RecyclerView.Adapter<UserProjectsAdapter.ViewHolder> {
    private List<UserProject> dataset;
    Context context;
    int visibility;
    private OnProjectClickListener mListener;

    public interface OnProjectClickListener{
        void onItemClick(int position);
    }

    public void setOnProjectClickListener(OnProjectClickListener listener){
        mListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView projectName;
        public TextView position;
        public TextView role;
        public IconTextView leaderIcon;

        public ViewHolder(View v, OnProjectClickListener listener) {
            super(v);
            projectName = v.findViewById(R.id.project_name);
            role = v.findViewById(R.id.role);
            position = v.findViewById(R.id.position);
            leaderIcon = v.findViewById(R.id.icon_leader);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public UserProjectsAdapter(Context context, int visibility) {
        this.context = context;
        this.visibility = visibility;
    }

    @Override
    public UserProjectsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user_project, parent, false);

        return new ViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        UserProject project = dataset.get(position);
        holder.position.setText(project.getPositionName());
        holder.projectName.setText(project.getName());
        holder.role.setText(project.getRoleName());
        holder.leaderIcon.setVisibility(visibility);
    }

    @Override
    public int getItemCount() {
        return dataset == null ? 0 : dataset.size();
    }

    public void setDataset(List<UserProject> dataset) {
        this.dataset = dataset;
    }
}