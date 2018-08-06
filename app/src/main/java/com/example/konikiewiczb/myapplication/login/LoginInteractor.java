package com.example.konikiewiczb.myapplication.login;

import android.preference.PreferenceManager;

import com.example.konikiewiczb.myapplication.framework.Api;
import com.example.konikiewiczb.myapplication.framework.IOnFinishedListener;
import com.example.konikiewiczb.myapplication.framework.RetrofitClient;
import com.example.konikiewiczb.myapplication.model.LoginResponse;
import com.example.konikiewiczb.myapplication.model.User;
import com.example.konikiewiczb.myapplication.model.UserRegistration;

import java.io.InputStream;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginInteractor implements LoginContract.Interactor {
    IOnFinishedListener<Response<String>> loginPresenter;

    public LoginInteractor(IOnFinishedListener<Response<String>> loginPresenter) {
        this.loginPresenter = loginPresenter;
    }

    @Override
    public void handleLogin(UserRegistration user) {
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
}
