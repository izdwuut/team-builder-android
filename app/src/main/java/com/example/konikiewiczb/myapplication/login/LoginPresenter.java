package com.example.konikiewiczb.myapplication.login;

import android.util.Log;

import com.example.konikiewiczb.myapplication.framework.Http;
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
    Repository<String> token;
    public LoginPresenter(LoginContract.View view, Repository<String> token) {
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
        view.displayMessage(response.body());
        if(Http.isCodeInRange(response.code(), 200)) {
            token.set("sztywny token azji");
            loadWelcomePage();
        } else {
            view.setError("email", "Niepoprawne dane.");
            view.setError("password", "Niepoprawne dane.");
        }
    }

    @Override
    public void onFailure(String message) {
        view.hideProgressBar();
        view.displayMessage(message);
    }
}
