package com.example.konikiewiczb.myapplication.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.konikiewiczb.myapplication.R;
import com.example.konikiewiczb.myapplication.framework.ProgressBarToggler;
import com.example.konikiewiczb.myapplication.model.Repository;
import com.example.konikiewiczb.myapplication.model.TokenRepository;
import com.example.konikiewiczb.myapplication.registration.RegistrationActivity;
import com.example.konikiewiczb.myapplication.projects.list.ProjectsListActivity;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends Activity implements LoginContract.View, View.OnClickListener {
    Button login;
    LoginPresenter loginPresenter;
    ProgressBar progressBar;
    Map<String, EditText> inputs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Repository tokenRepository = new TokenRepository(getApplicationContext());
        loginPresenter = new LoginPresenter(this, tokenRepository);
        loginPresenter.loadWelcomePage();
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.sign_in);
        login.setOnClickListener(this);
        progressBar = findViewById(R.id.progress_bar);
        hideProgressBar();

        inputs = new HashMap<>();
        inputs.put("email", (EditText) findViewById(R.id.email));
        inputs.put("password", (EditText) findViewById(R.id.password));
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void displayMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        showProgressBar();
        loginPresenter.handleLogin(inputs.get("email").getText().toString(), inputs.get("password").getText().toString());
    }

    @Override
    public void loadWelcomePage() {
        startActivity(new Intent(getApplicationContext(),ProjectsListActivity.class));
    }

    public InputStream getCert() {
        InputStream file = getResources().openRawResource(R.raw.cert);
        return file;
    }

    public void showRegistrationPage(View view) {
        startActivity(new Intent(getApplicationContext(),RegistrationActivity.class));
    }

    public void setError(String field, String error) {
        inputs.get(field).setError(error);
    }
}
