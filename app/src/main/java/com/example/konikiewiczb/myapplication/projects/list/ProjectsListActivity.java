package com.example.konikiewiczb.myapplication.projects.list;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.konikiewiczb.myapplication.MessageFragment;
import com.example.konikiewiczb.myapplication.R;
import com.example.konikiewiczb.myapplication.login.LoginActivity;
import com.example.konikiewiczb.myapplication.model.TokenRepository;
import com.example.konikiewiczb.myapplication.model.UserProject;

import java.io.InputStream;
import java.util.List;

public class ProjectsListActivity extends AppCompatActivity implements View.OnClickListener, ProjectsListContract.View, NavigationView.OnNavigationItemSelectedListener{
    Button logout;
    ListView usersList;
    ProjectsListContract.Presenter presenter;
    ProjectsListContract.Interactor interactor;
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

        logout = findViewById(R.id.logout);
        usersList = findViewById(R.id.users_list);

        logout.setOnClickListener(this);
        TokenRepository token = new TokenRepository(getApplicationContext());
        presenter = new ProjectsListPresenter(this, token);
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
            case R.id.nav_message:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MessageFragment()).commit();
                break;
        }
        return true;
    }

    @Override
    public void showProjectsList(List<UserProject> userProjects) {
        ArrayAdapter<UserProject> adapter = new ArrayAdapter<UserProject>(getApplicationContext(), android.R.layout.simple_list_item_1, userProjects) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater()
                            .inflate(R.layout.item_user_project, null, false);
                }
                UserProject project = this.getItem(position);

                TextView projectName = convertView.findViewById(R.id.project_name);
                projectName.setText(project.getName());
                TextView role = convertView.findViewById(R.id.role);
                role.setText(project.getRoleName());
                TextView positionName = convertView.findViewById(R.id.position);
                positionName.setText(project.getPositionName());
                int visible = View.INVISIBLE;
                if(project.getRoleName().equals(getString(R.string.top_role))) {
                    visible = View.VISIBLE;
                }
                convertView.findViewById(R.id.icon_star).setVisibility(visible);

                return convertView;
            }
        };
        usersList.setAdapter(adapter);
    }

    @Override
    public void displayMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
