package com.example.konikiewiczb.myapplication.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.konikiewiczb.myapplication.R;
import com.example.konikiewiczb.myapplication.model.Repository;
import com.example.konikiewiczb.myapplication.model.TokenRepository;
import com.example.konikiewiczb.myapplication.registration.RegistrationActivity;
import com.example.konikiewiczb.myapplication.welcome.WelcomeActivity;

import java.io.InputStream;

public class LoginActivity extends Activity implements LoginContract.View, View.OnClickListener {
    EditText email,password;
    Button login;
    LoginPresenter loginPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Repository tokenRepository = new TokenRepository(getApplicationContext());
        loginPresenter = new LoginPresenter(this, tokenRepository);
        loginPresenter.loadWelcomePage();
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.sign_in);
        login.setOnClickListener(this);
    }

    @Override
    public void displayMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        loginPresenter.handleLogin(email.getText().toString(), password.getText().toString());
    }

    @Override
    public void loadWelcomePage() {
        startActivity(new Intent(getApplicationContext(),WelcomeActivity.class));
    }

    public InputStream getCert() {
        InputStream file = getResources().openRawResource(R.raw.cert);
        return file;
    }

    public void showRegistrationPage(View view) {
        startActivity(new Intent(getApplicationContext(),RegistrationActivity.class));
    }
}
