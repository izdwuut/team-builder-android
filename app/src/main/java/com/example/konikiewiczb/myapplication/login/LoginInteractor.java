package com.example.konikiewiczb.myapplication.login;

import android.preference.PreferenceManager;

import com.example.konikiewiczb.myapplication.framework.Api;
import com.example.konikiewiczb.myapplication.framework.IOnFinishedListener;
import com.example.konikiewiczb.myapplication.framework.RetrofitClient;
import com.example.konikiewiczb.myapplication.model.LoginResponse;
import com.example.konikiewiczb.myapplication.model.User;

import java.io.InputStream;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginInteractor implements LoginContract.Interactor {
    IOnFinishedListener<Response<LoginResponse>> loginPresenter;

    public LoginInteractor(IOnFinishedListener<Response<LoginResponse>> loginPresenter) {
        this.loginPresenter = loginPresenter;
    }

    @Override
    public void handleLogin(User user) {
        Call<LoginResponse> call = RetrofitClient.get(Api.class)
                .login(user);

        call.enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                loginPresenter.onResponse(response);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                loginPresenter.onFailure(t.getMessage());
                t.printStackTrace();

            }
        });
    }
}
