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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistrationActivity extends Activity implements RegistrationView, View.OnClickListener {

    @BindView(R.id.etEmail) EditText etEmail;
    @BindView(R.id.etFirstname) EditText etFirstname;
    @BindView(R.id.etLastname) EditText etLastname;
    @BindView(R.id.etPwd) EditText etPwd;
    @BindView(R.id.etConfirmPwd) EditText etConfirmPwd;
    @BindView(R.id.progressBar2) ProgressBar progressBar;

    private RegistrationPresenter registrationPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);

        registrationPresenter = new RegistrationPresenterImpl(this);
    }

    @OnClick(R.id.bSendData)
    @Override
    public void onClick(View view) {
        showProgress();
        Extensions.hideKeyboard(this);
        registrationPresenter.validateData(etEmail.getText().toString(), etFirstname.getText().toString(), etLastname.getText().toString(), etPwd.getText().toString(), etConfirmPwd.getText().toString());
    }

    @Override
    public void showEmailError() {
        etEmail.setError(getString(R.string.email_error));
        hideProgress();
    }

    @Override
    public void showFirstnameError() {
        etFirstname.setError(getString(R.string.firstname_error));
        hideProgress();
    }

    @Override
    public void showLastnameError() {
        etLastname.setError(getString(R.string.lastname_error));
        hideProgress();
    }

    @Override
    public void showPasswordError() {
        etPwd.setError(getString(R.string.password_error));
        hideProgress();
    }

    @Override
    public void showConfirmPasswordError() {
        etConfirmPwd.setError(getString(R.string.confirm_password_error));
        hideProgress();
    }

    @Override
    public void showSuccess() {
        final Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.registration_successful))
                .setNegativeButton(getString(R.string.negative_button), new DialogInterface.OnClickListener() {
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
        builder.setMessage(getString(R.string.registration_failed))
                .setNegativeButton(getString(R.string.negative_button), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        RegistrationActivity.this.startActivity(intent);
                    }
                })
                .create()
                .show();
    }

    @OnClick(R.id.bCancel)
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
