package com.example.konikiewiczb.myapplication.welcome;

import com.example.konikiewiczb.myapplication.model.User;

import java.io.InputStream;
import java.util.List;

public interface WelcomeContract {
    interface View {
         void showUsersList(List<User> users);
         InputStream getCert();
         void displayMessage(String message);
    }

    interface Presenter {
        void logOut();
        void getUsersList();
    }

    interface Interactor {
        void getUsersList(String token);
    }
}
