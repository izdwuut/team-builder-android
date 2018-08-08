package com.example.konikiewiczb.myapplication.projects.list;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.util.Log;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.konikiewiczb.myapplication.ChatFragment;
import com.example.konikiewiczb.myapplication.MessageFragment;
import com.example.konikiewiczb.myapplication.ProfileFragment;
import com.example.konikiewiczb.myapplication.R;
import com.example.konikiewiczb.myapplication.login.LoginActivity;
import com.example.konikiewiczb.myapplication.model.TeamLeaderRepository;
import com.example.konikiewiczb.myapplication.model.TokenRepository;
import com.example.konikiewiczb.myapplication.model.UserProject;

import java.util.List;

public class ProjectsListActivity extends AppCompatActivity implements View.OnClickListener, ProjectsListContract.View, NavigationView.OnNavigationItemSelectedListener{
    Button logout;
    RecyclerView memberProjects, leaderProjects;
    ProjectsListContract.Presenter presenter;
    ProgressBar progressBar;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new MessageFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_profile);
        }

        logout = findViewById(R.id.logout);
        progressBar = findViewById(R.id.progress_bar);
        showProgressBar();
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
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View view) {
        presenter.logOut();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MessageFragment()).commit();
                break;
            case R.id.nav_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ChatFragment()).commit();
                break;
            case R.id.nav_all:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProfileFragment()).commit();
                break;
            case R.id.nav_signout:
                Toast.makeText(this, "Singout",Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_create:
                Toast.makeText(this, "Create",Toast.LENGTH_LONG).show();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
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
