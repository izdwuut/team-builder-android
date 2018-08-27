package com.example.konikiewiczb.myapplication.registration;

import com.example.konikiewiczb.myapplication.framework.views.progress_bar.ProgressBarHandler;

public interface RegistrationView extends ProgressBarHandler{

    void showEmailError();

    void showFirstnameError();

    void showLastnameError();

    void showPasswordError();

    void showConfirmPasswordError();

    void showSuccess();

    void showFailureAler();

    void navigateToLogin();
}
