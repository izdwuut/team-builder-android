package com.example.konikiewiczb.myapplication.welcome;

import com.example.konikiewiczb.myapplication.framework.IOnFinishedListener;
import com.example.konikiewiczb.myapplication.model.Repository;
import com.example.konikiewiczb.myapplication.model.TokenRepository;
import com.example.konikiewiczb.myapplication.model.User;

import java.util.List;

import retrofit2.Response;

public class WelcomePresenter implements WelcomeContract.Presenter, IOnFinishedListener<Response<List<User>>> {
    Repository<String> token;
    WelcomeContract.View view;
    WelcomeContract.Interactor interactor;

    public WelcomePresenter(WelcomeContract.View view, Repository<String> token) {
        this.token = token;
        this.view = view;
        this.interactor = new WelcomeInteractor(this, view.getCert());
    }
    @Override
    public void logOut() {
        token.remove();
    }

    @Override
    public void getUsersList() {
        interactor.getUsersList(token.get());
    }

    @Override
    public void onResponse(Response<List<User>> response) {
        view.showUsersList(response.body());
    }

    @Override
    public void onFailure(String message) {
        view.displayMessage(message);
    }
}
