package com.example.konikiewiczb.myapplication.projects.list;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.konikiewiczb.myapplication.R;
import com.example.konikiewiczb.myapplication.login.LoginActivity;
import com.example.konikiewiczb.myapplication.model.TeamLeaderRepository;
import com.example.konikiewiczb.myapplication.model.TokenRepository;
import com.example.konikiewiczb.myapplication.model.UserProject;

import java.util.List;

public class ProjectsListActivity extends Activity implements View.OnClickListener, ProjectsListContract.View {
    Button logout;
    RecyclerView memberProjects, leaderProjects;
    ProjectsListContract.Presenter presenter;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects_list);
        logout = findViewById(R.id.logout);
        leaderProjects = findViewById(R.id.leader_projects);
        leaderProjects.setAdapter(new UserProjectsAdapter(getApplicationContext(), View.VISIBLE));
        leaderProjects.setLayoutManager(new LinearLayoutManager(this));
        memberProjects = findViewById(R.id.member_projects);
        memberProjects.setAdapter(new UserProjectsAdapter(getApplicationContext(), View.INVISIBLE));
        memberProjects.setLayoutManager(new LinearLayoutManager(this));

        logout.setOnClickListener(this);
        Context context = getApplicationContext();
        TokenRepository token = new TokenRepository(context);
        TeamLeaderRepository teamLeader = new TeamLeaderRepository(context);
        presenter = new ProjectsListPresenter(this, token, teamLeader);
        presenter.getProjectsList();
    }

    @Override
    public void onClick(View view) {
        presenter.logOut();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }

    @Override
    public void displayMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLeaderProjectsList(List<UserProject> projects) {
        setDataset(leaderProjects, projects);
    }

    @Override
    public void showMemberProjectsList(List<UserProject> projects) {
        setDataset(memberProjects, projects);
    }

    void setDataset(RecyclerView view, List<UserProject> dataset) {
        UserProjectsAdapter adapter = (UserProjectsAdapter) view.getAdapter();
        adapter.setDataset(dataset);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }
}
