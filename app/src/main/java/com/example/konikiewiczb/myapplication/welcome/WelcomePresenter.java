package com.example.konikiewiczb.myapplication.welcome;

import com.example.konikiewiczb.myapplication.model.Repository;
import com.example.konikiewiczb.myapplication.model.TokenRepository;

public class WelcomePresenter implements WelcomeContract.Presenter {
    Repository token;
    public WelcomePresenter(Repository token) {
        this.token = token;
    }
    @Override
    public void logOut() {
        token.remove();
    }
}
