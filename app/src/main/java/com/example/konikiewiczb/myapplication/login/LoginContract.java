package com.example.konikiewiczb.myapplication.login;

import com.example.konikiewiczb.myapplication.framework.IOnFinishedLoginListener;
import com.example.konikiewiczb.myapplication.framework.ProgressBarToggler;
import com.example.konikiewiczb.myapplication.model.User;

import retrofit2.Response;

public interface LoginContract {
    interface View extends ProgressBarToggler {
        void displayMessage(String message);

        void loadWelcomePage();

        void setError(String field, String error);
    }

    interface Presenter extends IOnFinishedLoginListener {
        void handleLogin(String login, String password);
        void loadWelcomePage();
        void saveUser(Response<User> user);
    }

    interface Interactor {
        void handleLogin(User user);
        void getUser(String email);
    }
}
