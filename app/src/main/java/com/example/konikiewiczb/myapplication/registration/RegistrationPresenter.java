package com.example.konikiewiczb.myapplication.registration;

public interface RegistrationPresenter {

    void validateData(String emailAdress, String firstname, String lastname, String password, String confirmPassword);

    void onDestroy();
}
