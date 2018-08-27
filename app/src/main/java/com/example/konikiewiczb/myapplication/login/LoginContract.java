package com.example.konikiewiczb.myapplication.login;

import com.example.konikiewiczb.myapplication.framework.views.progress_bar.ProgressBarHandler;
import com.example.konikiewiczb.myapplication.model.User;

import retrofit2.Response;

public interface LoginContract {
    interface View extends ProgressBarHandler {
        void displayMessage(String message);

        void loadWelcomePage();
        void setEmailError();
        void setPasswordError();
        void showSaveUserError();
    }

    interface Presenter {
        void handleLogin(String login, String password);
        void loadWelcomePage();
        void saveUser(Response<User> user);
        void onFailure(String message);
        void logIn(Response<String> user);
    }

    interface Interactor {
        void handleLogin(User user);
        void getUser(String email);
    }
}
