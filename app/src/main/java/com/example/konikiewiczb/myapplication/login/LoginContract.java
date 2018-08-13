package com.example.konikiewiczb.myapplication.login;

import com.example.konikiewiczb.myapplication.framework.ProgressBarToggler;
import com.example.konikiewiczb.myapplication.model.User;

public interface LoginContract {
    interface View extends ProgressBarToggler {
        void displayMessage(String message);

        void loadWelcomePage();

        void setError(String field, String error);
    }

    interface Presenter {
        void handleLogin(String login, String password);
        void loadWelcomePage();
    }

    interface Interactor {
        void handleLogin(User user);
    }
}
