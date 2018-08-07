package com.example.konikiewiczb.myapplication.projects.list;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.konikiewiczb.myapplication.R;
import com.example.konikiewiczb.myapplication.login.LoginActivity;
import com.example.konikiewiczb.myapplication.model.TokenRepository;
import com.example.konikiewiczb.myapplication.model.UserProject;

import java.io.InputStream;
import java.util.List;

public class ProjectsListActivity extends Activity implements View.OnClickListener, ProjectsListContract.View {
    Button logout;
    RecyclerView usersList;
    ProjectsListContract.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects_list);
        logout = findViewById(R.id.logout);
        usersList = findViewById(R.id.users_list);
        usersList.setLayoutManager(new LinearLayoutManager(this));

        logout.setOnClickListener(this);
        TokenRepository token = new TokenRepository(getApplicationContext());
        presenter = new ProjectsListPresenter(this, token);
        presenter.getProjectsList();
    }

    @Override
    public void onClick(View view) {
        presenter.logOut();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }

    @Override
    public void showProjectsList(List<UserProject> userProjects) {
        Adapter adapter = new UserProjectsAdapter(userProjects, getApplicationContext());
        usersList.setAdapter(adapter);
    }

    @Override
    public void displayMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
