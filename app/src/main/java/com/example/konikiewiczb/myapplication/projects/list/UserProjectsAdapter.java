package com.example.konikiewiczb.myapplication.projects.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.konikiewiczb.myapplication.R;
import com.example.konikiewiczb.myapplication.model.UserProject;

import java.util.List;

public class UserProjectsAdapter extends RecyclerView.Adapter<UserProjectsAdapter.ViewHolder> {
    private List<UserProject> dataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView projectName;
        public TextView position;
        public TextView role;

        public ViewHolder(View v) {
            super(v);
            projectName = v.findViewById(R.id.project_name);
            role = v.findViewById(R.id.role);
            position = v.findViewById(R.id.position);
        }
    }

    public UserProjectsAdapter(List<UserProject> dataset) {
        this.dataset = dataset;
    }

    @Override
    public UserProjectsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user_project, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        UserProject project = dataset.get(position);
        holder.position.setText(project.getPositionName());
        holder.projectName.setText(project.getName());
        holder.role.setText(project.getRoleName());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}