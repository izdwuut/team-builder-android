package com.example.konikiewiczb.myapplication.login;

import android.util.Log;

import com.example.konikiewiczb.myapplication.framework.Http;
import com.example.konikiewiczb.myapplication.framework.IOnFinishedLoginListener;
import com.example.konikiewiczb.myapplication.model.User;
import com.example.konikiewiczb.myapplication.model.repositories.Repository;

import retrofit2.Response;

public class LoginPresenter implements LoginContract.Presenter, IOnFinishedLoginListener {
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
    public void onResponse(Response<String> response) {
        view.hideProgressBar();
        view.displayMessage(response.body());
        if(Http.isCodeInRange(response.code(), 200)) {
            Log.d("email:", this.user.getEmailAddress());
            interactor.getUser(this.user.getEmailAddress());
        } else {
            view.setError("email", "Niepoprawne dane.");
            view.setError("password", "Niepoprawne dane.");
            userRepository.remove();
        }
    }

    @Override
    public void onFailure(String message) {
        view.hideProgressBar();
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
            view.displayMessage("Wystąpił błąd. Spróbuj ponownie.");
        }
    }
}
