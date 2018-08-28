package com.example.konikiewiczb.myapplication.login;

import android.util.Log;

import com.auth0.android.jwt.JWT;
import com.example.konikiewiczb.myapplication.framework.http.Http;
import com.example.konikiewiczb.myapplication.model.User;
import com.example.konikiewiczb.myapplication.model.repositories.Repository;

import retrofit2.Response;

public class LoginPresenter implements LoginContract.Presenter {
    LoginContract.View view;
    LoginContract.Interactor interactor;
    Repository<User> userRepository;
    Repository<String> tokenRepository;
    User user;
    public LoginPresenter(LoginContract.View view, Repository<User> userRepository, Repository<String> tokenRepository) {
        this.view = view;
        interactor = new LoginInteractor(this, userRepository);
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public void handleLogin(String login, String password) {
        this.user = new User(login, password);
        interactor.handleLogin(user);
    }

    @Override
    public void loadWelcomePage() {
        if(userRepository.isSet() && tokenRepository.isSet()) {
            view.loadWelcomePage();
        }
    }

    @Override
    public void logIn(Response<String> response) {
        if(Http.isCodeInRange(response.code(), 200)) {
            tokenRepository.set(response.body());
            interactor.getUser(this.user.getEmailAddress());
        } else {
            view.setEmailError();
            view.setPasswordError();
            tokenRepository.remove();
            view.getProgressBar().hide();
        }
    }

    @Override
    public void onFailure(String message) {
        view.getProgressBar().hide();
        view.displayMessage(message);
        userRepository.remove();
    }


    @Override
    public void saveUser(Response<User> response) {
        view.getProgressBar().hide();
        if(Http.isCodeInRange(response.code(), 200)) {
            userRepository.set(response.body());
            loadWelcomePage();
        } else {
            view.showSaveUserError();
        }
    }
}
