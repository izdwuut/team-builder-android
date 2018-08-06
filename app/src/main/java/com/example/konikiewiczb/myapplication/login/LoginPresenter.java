package com.example.konikiewiczb.myapplication.login;

import android.util.Log;

import com.example.konikiewiczb.myapplication.framework.IOnFinishedListener;
import com.example.konikiewiczb.myapplication.model.LoginResponse;
import com.example.konikiewiczb.myapplication.model.Repository;
import com.example.konikiewiczb.myapplication.model.User;
import com.example.konikiewiczb.myapplication.model.UserRegistration;
import com.example.konikiewiczb.myapplication.registration.RegistrationActivity;

import retrofit2.Response;

public class LoginPresenter implements LoginContract.Presenter, IOnFinishedListener<Response<String>> {
    LoginContract.View view;
    LoginContract.Interactor interactor;
    Repository token;
    public LoginPresenter(LoginContract.View view, Repository token) {
        this.view = view;
        interactor = new LoginInteractor(this);
        this.token = token;
    }

    @Override
    public void handleLogin(String login, String password) {
        interactor.handleLogin(new UserRegistration(login, password));
    }

    @Override
    public void loadWelcomePage() {
        if(token.isSet()) {
            view.loadWelcomePage();
        }
    }

    @Override
    public void onResponse(Response<String> response) {
        view.hideProgressBar();
        Log.d("Response:", String.valueOf(response.code()));
        if(response.code() == 200) {
            view.displayMessage(response.body());
            view.loadWelcomePage();
        } else {
            view.displayMessage("Niepoprawne dane.");
        }
    }

    @Override
    public void onFailure(String message) {
        view.hideProgressBar();
        view.displayMessage(message);
    }
}
