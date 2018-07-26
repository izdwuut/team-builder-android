package com.example.konikiewiczb.myapplication.welcome;

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
import com.example.konikiewiczb.myapplication.model.User;

import java.io.InputStream;
import java.util.List;

public class WelcomeActivity extends Activity implements View.OnClickListener, WelcomeContract.View {
    Button logout;
    ListView usersList;
    WelcomeContract.Presenter presenter;
    WelcomeContract.Interactor interactor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        logout = findViewById(R.id.logout);
        usersList = findViewById(R.id.users_list);

        logout.setOnClickListener(this);
        presenter = new WelcomePresenter(this, new TokenRepository(getApplicationContext()));
        presenter.getUsersList();
    }

    @Override
    public void onClick(View view) {
        presenter.logOut();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }

    @Override
    public void showUsersList(List<User> users) {
        ArrayAdapter<User> adapter = new ArrayAdapter<User>(getApplicationContext(), android.R.layout.simple_list_item_1, users) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater()
                            .inflate(R.layout.item_user, null, false);
                }
                User user = this.getItem(position);
                TextView name = convertView.findViewById(R.id.user_name);
                String userName = user.getName() + ' ' + user.getSurname();
                name.setText(userName);

                return convertView;
            }
        };
        usersList.setAdapter(adapter);
    }

    @Override
    public InputStream getCert() {
        InputStream file = getResources().openRawResource(R.raw.cert);
        return file;
    }

    @Override
    public void displayMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
