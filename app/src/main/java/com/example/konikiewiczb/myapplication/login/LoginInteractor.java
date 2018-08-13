package com.example.konikiewiczb.myapplication.login;

import com.example.konikiewiczb.myapplication.framework.Api;
import com.example.konikiewiczb.myapplication.framework.IOnFinishedLoginListener;
import com.example.konikiewiczb.myapplication.framework.RetrofitClient;
import com.example.konikiewiczb.myapplication.model.repositories.Repository;
import com.example.konikiewiczb.myapplication.model.UserRegistration;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginInteractor implements LoginContract.Interactor {
    IOnFinishedLoginListener loginPresenter;
    Repository token;

    public LoginInteractor(IOnFinishedLoginListener loginPresenter, Repository token) {
        this.loginPresenter = loginPresenter;
        this.token = token;
    }

    @Override
    public void handleLogin(UserRegistration user) {
        Call<String> call = RetrofitClient.get(Api.class)
                .login(user);
        token.set(user.getEmailAddress());
        call.enqueue(new Callback<String>() {

            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                loginPresenter.onResponse(response);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                loginPresenter.onFailure(t.getMessage());
                t.printStackTrace();

            }
        });
    }
}
