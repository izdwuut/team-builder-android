package com.example.konikiewiczb.myapplication.projects.list.adapter;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.konikiewiczb.myapplication.R;
import com.example.konikiewiczb.myapplication.framework.adapter.GenericViewHolder;
import com.example.konikiewiczb.myapplication.framework.view.IconTextView;
import com.example.konikiewiczb.myapplication.model.UserProject;
import com.example.konikiewiczb.myapplication.projects.project.ProjectFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class UserProjectsViewHolder extends GenericViewHolder {
    @BindView(R.id.project_name) public TextView projectName;
    @BindView(R.id.position) public TextView position;
    @BindView(R.id.role) public TextView role;
    @BindView(R.id.icon_leader) public IconTextView leaderIcon;

    FragmentManager fragmentManager;
    List<UserProject> dataset;

    public UserProjectsViewHolder(View v, FragmentManager fragmentManager, List<UserProject> dataset) {
        super(v);
        this.fragmentManager = fragmentManager;
        this.dataset = dataset;
    }

    @OnClick
    public void onClick() {
        int position = getAdapterPosition();
        if(position != RecyclerView.NO_POSITION){
            UserProject item = dataset.get(position);
            ProjectFragment fragment = new ProjectFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("id", item.getProjectId());
            fragment.setArguments(bundle);

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
        }
    }
}
