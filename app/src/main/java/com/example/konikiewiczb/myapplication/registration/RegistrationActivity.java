package com.example.konikiewiczb.myapplication.registration;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.konikiewiczb.myapplication.R;

public class RegistrationActivity extends Activity implements RegistrationView, View.OnClickListener {

    private EditText etEmail;
    private EditText etFirstname;
    private EditText etLastname;
    private EditText etPwd;
    private EditText etConfirmPwd;

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

        registrationPresenter = new RegistrationPresenterImpl(this);

        findViewById(R.id.bSendData).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Log.d("RegistrationActivity","\n-------------------------\n" + "LISTENER ON CLICK" + "\n-------------------------\n");
        registrationPresenter.validateData(etEmail.getText().toString(), etFirstname.getText().toString(), etLastname.getText().toString(), etPwd.getText().toString(), etConfirmPwd.getText().toString());
    }

    @Override
    public void showEmailError() {

    }

    @Override
    public void showFirstnameError() {

    }

    @Override
    public void showLastnameError() {

    }

    @Override
    public void showPasswordError() {

    }

    @Override
    public void showConfirmPasswordError() {

    }

    @Override
    public void showSuccess() {

    }

    @Override
    public void navigateToLogin() {

    }
}
