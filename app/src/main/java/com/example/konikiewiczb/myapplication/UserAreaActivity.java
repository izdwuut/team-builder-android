package com.example.konikiewiczb.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.konikiewiczb.myapplication.coworkers.CoWorkersFragment;
import com.example.konikiewiczb.myapplication.login.LoginActivity;
import com.example.konikiewiczb.myapplication.model.User;
import com.example.konikiewiczb.myapplication.model.repositories.Repository;
import com.example.konikiewiczb.myapplication.model.repositories.UserRepository;
import com.example.konikiewiczb.myapplication.projects.list.ProjectsListsFragment;

public class UserAreaActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    private Repository<User> userRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

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
                    new ProjectsListsFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_project_list);
        }
        userRepository = new UserRepository(getApplicationContext());
        showUserInfo(navigationView.getHeaderView(0), userRepository.get());
    }

    void showUserInfo(View header, User user) {
        TextView firstLastName = header.findViewById(R.id.first_last_name);
        TextView email = header.findViewById(R.id.email);
        firstLastName.setText(user.toString());
        email.setText(user.getEmailAddress());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_project_list:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProjectsListsFragment()).commit();
                break;

            case R.id.nav_coworkers:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new CoWorkersFragment()).commit();
                break;

            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProfileFragment()).commit();
                break;

            case R.id.nav_sign_out:
                userRepository.remove();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            this.moveTaskToBack(true);
        }
    }

}
