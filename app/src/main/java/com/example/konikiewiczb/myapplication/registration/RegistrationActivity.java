package com.example.konikiewiczb.myapplication.registration;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.konikiewiczb.myapplication.framework.Extensions;
import com.example.konikiewiczb.myapplication.R;
import com.example.konikiewiczb.myapplication.login.LoginActivity;

public class RegistrationActivity extends Activity implements RegistrationView, View.OnClickListener {

    private EditText etEmail;
    private EditText etFirstname;
    private EditText etLastname;
    private EditText etPwd;
    private EditText etConfirmPwd;
    private ProgressBar progressBar;

    private RegistrationPresenter registrationPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etFirstname = (EditText) findViewById(R.id.etFirstname);
        etLastname = (EditText) findViewById(R.id.etLastname);
        etPwd = (EditText) findViewById(R.id.etPwd);
        etConfirmPwd = (EditText) findViewById(R.id.etConfirmPwd);
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);

        registrationPresenter = new RegistrationPresenterImpl(this);

        findViewById(R.id.bSendData).setOnClickListener(this);

        Button bCancel = (Button) findViewById(R.id.bCancel);
        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToLogin();
            }
        });

    }

    @Override
    public void onClick(View view) {
        showProgress();
        Extensions.hideKeyboard(this);
        registrationPresenter.validateData(etEmail.getText().toString(), etFirstname.getText().toString(), etLastname.getText().toString(), etPwd.getText().toString(), etConfirmPwd.getText().toString());
    }

    @Override
    public void showEmailError() {
        etEmail.setError("Podany adres E-mail jest niepoprawny!");
        hideProgress();
    }

    @Override
    public void showFirstnameError() {
        etFirstname.setError("To pole nie może być puste!");
        hideProgress();
    }

    @Override
    public void showLastnameError() {
        etLastname.setError("To pole nie może być puste!");
        hideProgress();
    }

    @Override
    public void showPasswordError() {
        etPwd.setError("Podane hasło jest nie prawidłowe!");
        hideProgress();
    }

    @Override
    public void showConfirmPasswordError() {
        etConfirmPwd.setError("Hasła muszą być takie same!");
        hideProgress();
    }

    @Override
    public void showSuccess() {
        final Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Rejestracja zakończone sukcesem.\nNa podany adres e-mail wysłaliśmy link aktywacyjny.")
                .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        RegistrationActivity.this.startActivity(intent);
                    }
                })
                .create()
                .show();
    }

    @Override
    public void showFailureAler() {
        hideProgress();
        final Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        RegistrationActivity.this.startActivity(intent);
        builder.setMessage("Niestety rejestracja się nie powiodła.\nPrzepraszamy")
                .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        RegistrationActivity.this.startActivity(intent);
                    }
                })
                .create()
                .show();
    }

    @Override
    public void navigateToLogin() {
        hideProgress();
        Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
        RegistrationActivity.this.startActivity(intent);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }
}
