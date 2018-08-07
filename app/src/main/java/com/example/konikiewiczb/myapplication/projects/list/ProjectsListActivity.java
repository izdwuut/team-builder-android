package com.example.konikiewiczb.myapplication.projects.list;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
    ListView usersList;
    ProjectsListContract.Presenter presenter;
    ProjectsListContract.Interactor interactor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects_list);
        logout = findViewById(R.id.logout);
        usersList = findViewById(R.id.users_list);

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
