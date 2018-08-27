package com.example.konikiewiczb.myapplication.projects.list.adapter;

import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.konikiewiczb.myapplication.R;
import com.example.konikiewiczb.myapplication.framework.adapter.GenericAdapter;
import com.example.konikiewiczb.myapplication.model.UserProject;

import org.jetbrains.annotations.NotNull;

public class UserProjectsAdapter extends GenericAdapter<UserProject, UserProjectsViewHolder> {

    int visibility;

    public UserProjectsAdapter(int visibility, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.visibility = visibility;
    }

    @Override
    public UserProjectsViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user_project, parent, false);

        return new UserProjectsViewHolder(v, fragmentManager, dataset);
    }

    @Override
    public void onBindViewHolder(UserProjectsViewHolder holder, int position) {
        UserProject project = dataset.get(position);
        holder.position.setText(project.getPositionName());
        holder.projectName.setText(project.getName());
        holder.role.setText(project.getRoleName());
        holder.leaderIcon.setVisibility(visibility);
    }
}