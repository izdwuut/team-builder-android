package com.example.konikiewiczb.myapplication.registration;

public interface RegistrationView {

    void showEmailError();

    void showFirstnameError();

    void showLastnameError();

    void showPasswordError();

    void showConfirmPasswordError();

    void showSuccess();

    void navigateToLogin();

}
