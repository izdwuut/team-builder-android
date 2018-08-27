package com.example.konikiewiczb.myapplication.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.konikiewiczb.myapplication.R;
import com.example.konikiewiczb.myapplication.UserAreaActivity;
import com.example.konikiewiczb.myapplication.framework.views.GenericActivity;
import com.example.konikiewiczb.myapplication.model.User;
import com.example.konikiewiczb.myapplication.model.repositories.Repository;
import com.example.konikiewiczb.myapplication.model.repositories.UserRepository;
import com.example.konikiewiczb.myapplication.registration.RegistrationActivity;

import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends GenericActivity implements LoginContract.View, View.OnClickListener {
    @BindView(R.id.sign_in) Button login;
    LoginPresenter loginPresenter;
    @BindView(R.id.email) EditText email;
    @BindView(R.id.password) EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        Repository<User> userRepository = new UserRepository(getApplicationContext());
        loginPresenter = new LoginPresenter(this, userRepository);
        loginPresenter.loadWelcomePage();

        ButterKnife.bind(this);
    }

    @Override
    public void displayMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.sign_in)
    @Override
    public void onClick(View view) {
        progressBar.show();
        loginPresenter.handleLogin(email.getText().toString(), password.getText().toString());
    }

    @Override
    public void loadWelcomePage() {
        startActivity(new Intent(getApplicationContext(),UserAreaActivity.class));
    }

    @Override
    public void setEmailError() {
        setError(email, getString(R.string.email_error));
    }

    @Override
    public void setPasswordError() {
        setError(password, getString(R.string.password_error));
    }

    @Override
    public void showSaveUserError() {
        displayMessage(getString(R.string.generic_error));
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
