package com.example.konikiewiczb.myapplication.login;

import com.example.konikiewiczb.myapplication.framework.IOnFinishedListener;
import com.example.konikiewiczb.myapplication.model.LoginResponse;
import com.example.konikiewiczb.myapplication.model.Repository;
import com.example.konikiewiczb.myapplication.model.User;

import retrofit2.Response;

public class LoginPresenter implements LoginContract.Presenter, IOnFinishedListener<Response<LoginResponse>> {
    LoginContract.View view;
    LoginContract.Interactor interactor;
    Repository token;
    public LoginPresenter(LoginContract.View view, Repository token) {
        this.view = view;
        interactor = new LoginInteractor(this, view.getKeyStore());
        this.token = token;
    }

    @Override
    public void handleLogin(String login, String password) {
        interactor.handleLogin(new User(login, password));
    }

    @Override
    public void loadWelcomePage() {
        if(token.isSet()) {
            view.loadWelcomePage();
        }
    }

    @Override
    public void onResponse(Response<LoginResponse> response) {
        String tokenResponse = response.body().getSuccess();
        if(tokenResponse.isEmpty()) {
            view.displayMessage("Invalid credentials.");
        } else {
            token.set(tokenResponse);
            view.loadWelcomePage();
        }
    }

    @Override
    public void onFailure(String message) {
        view.displayMessage(message);
    }
}
