package com.example.konikiewiczb.myapplication.login;

import com.example.konikiewiczb.myapplication.model.User;

import java.io.InputStream;

public interface LoginContract {
    interface View {
        void displayMessage(String message);

        void loadWelcomePage();

        InputStream getCert();

        void hideProgressBar();
    }

    interface Presenter {
        void handleLogin(String login, String password);
        void loadWelcomePage();
    }

    interface Interactor {
        void handleLogin(User user);
    }
}
