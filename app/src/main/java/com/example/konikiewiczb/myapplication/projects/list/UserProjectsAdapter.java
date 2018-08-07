package com.example.konikiewiczb.myapplication.projects.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.konikiewiczb.myapplication.R;
import com.example.konikiewiczb.myapplication.framework.views.IconTextView;
import com.example.konikiewiczb.myapplication.model.UserProject;

import java.util.List;

public class UserProjectsAdapter extends RecyclerView.Adapter<UserProjectsAdapter.ViewHolder> {
    private List<UserProject> dataset;
    Context context;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView projectName;
        public TextView position;
        public TextView role;
        public IconTextView leaderIcon;

        public ViewHolder(View v) {
            super(v);
            projectName = v.findViewById(R.id.project_name);
            role = v.findViewById(R.id.role);
            position = v.findViewById(R.id.position);
            leaderIcon = v.findViewById(R.id.icon_leader);
        }
    }

    public UserProjectsAdapter(List<UserProject> dataset, Context context) {
        this.dataset = dataset;
        this.context = context;
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
        String role = project.getRoleName();
        holder.role.setText(project.getRoleName());
        int visible = View.INVISIBLE;
        if(role.equals(context.getString(R.string.top_role))) {
            visible = View.VISIBLE;
        }
        holder.leaderIcon.setVisibility(visible);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}