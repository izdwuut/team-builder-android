package com.example.konikiewiczb.myapplication.login;

import android.util.Log;

import com.example.konikiewiczb.myapplication.framework.http.Http;
import com.example.konikiewiczb.myapplication.model.User;
import com.example.konikiewiczb.myapplication.model.repositories.Repository;

import retrofit2.Response;

public class LoginPresenter implements LoginContract.Presenter {
    LoginContract.View view;
    LoginContract.Interactor interactor;
    Repository<User> userRepository;
    User user;
    public LoginPresenter(LoginContract.View view, Repository<User> userRepository) {
        this.view = view;
        interactor = new LoginInteractor(this, userRepository);
        this.userRepository = userRepository;
    }

    @Override
    public void handleLogin(String login, String password) {
        this.user = new User(login, password);
        interactor.handleLogin(user);
    }

    @Override
    public void loadWelcomePage() {
        if(userRepository.isSet()) {
            view.loadWelcomePage();
        }
    }

    @Override
    public void logIn(Response<String> response) {
        view.getProgressBar().hide();
        view.displayMessage(response.body());
        if(Http.isCodeInRange(response.code(), 200)) {
            interactor.getUser(this.user.getEmailAddress());
        } else {
            view.setEmailError();
            view.setPasswordError();
            userRepository.remove();
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
        if(Http.isCodeInRange(response.code(), 200)) {
            Log.d("mail", response.body().getEmailAddress());
            userRepository.set(response.body());
            loadWelcomePage();
        } else {
            view.showSaveUserError();
        }
    }
}
