package com.example.konikiewiczb.myapplication.login;

import android.util.Log;

import com.example.konikiewiczb.myapplication.framework.Api;
import com.example.konikiewiczb.myapplication.framework.IOnFinishedLoginListener;
import com.example.konikiewiczb.myapplication.framework.RetrofitClient;
import com.example.konikiewiczb.myapplication.model.repositories.Repository;
import com.example.konikiewiczb.myapplication.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginInteractor implements LoginContract.Interactor {
    LoginContract.Presenter loginPresenter;
    Repository<User> user;
    public LoginInteractor(LoginContract.Presenter loginPresenter, Repository<User> user) {
        this.loginPresenter = loginPresenter;
        this.user = user;
    }

    @Override
    public void handleLogin(User user) {
        Call<String> call = RetrofitClient.get(Api.class)
                .login(user);
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

    @Override
    public void getUser(String email) {
        Call<User> call = RetrofitClient.get(Api.class)
                .getUser(email);
        call.enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d("RESPonse", response.body().getFirstname());
                loginPresenter.saveUser(response);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                loginPresenter.onFailure(t.getMessage());
                t.printStackTrace();

            }
        });
    }
}
