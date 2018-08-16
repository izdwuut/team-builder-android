package com.example.konikiewiczb.myapplication.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.konikiewiczb.myapplication.R;
import com.example.konikiewiczb.myapplication.UserAreaActivity;
import com.example.konikiewiczb.myapplication.model.User;
import com.example.konikiewiczb.myapplication.model.repositories.Repository;
import com.example.konikiewiczb.myapplication.model.repositories.TokenRepository;
import com.example.konikiewiczb.myapplication.model.repositories.UserRepository;
import com.example.konikiewiczb.myapplication.registration.RegistrationActivity;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends Activity implements LoginContract.View, View.OnClickListener {
    Button login;
    LoginPresenter loginPresenter;
    ProgressBar progressBar;
    Map<String, EditText> inputs;
    EditText email, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Repository<User> userRepository = new UserRepository(getApplicationContext());
        loginPresenter = new LoginPresenter(this, userRepository);
        loginPresenter.loadWelcomePage();
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.sign_in);
        login.setOnClickListener(this);
        progressBar = findViewById(R.id.progress_bar);
        hideProgressBar();
        inputs = new HashMap<>();
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
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
        loginPresenter.handleLogin(email.getText().toString(), password.getText().toString());
    }

    @Override
    public void loadWelcomePage() {
        startActivity(new Intent(getApplicationContext(),UserAreaActivity.class));
    }

    @Override
    public void setEmailError() {
        setError(email, "Niepoprawne dane.");
    }

    @Override
    public void setPasswordError() {
        setError(password, "Niepoprawne dane.");
    }

    @Override
    public void showSaveUserError() {
        displayMessage("Wystąpił błąd. Spróbuj ponownie.");
    }

    public InputStream getCert() {
        InputStream file = getResources().openRawResource(R.raw.cert);
        return file;
    }

    public void showRegistrationPage(View view) {
        startActivity(new Intent(getApplicationContext(),RegistrationActivity.class));
    }

    void setError(EditText field, String error) {
        field.setError(error);
    }
}
