package com.example.konikiewiczb.myapplication.login;

import android.preference.PreferenceManager;

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
    InputStream cert;

    public LoginInteractor(IOnFinishedListener<Response<LoginResponse>> loginPresenter, InputStream cert) {
        this.loginPresenter = loginPresenter;
        this.cert = cert;
    }

    @Override
    public void handleLogin(User user) {
        Call<LoginResponse> call = RetrofitClient.getApi(cert)
                .login(user);

        call.enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                loginPresenter.onResponse(response);
                System.out.println("response: " + response);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                loginPresenter.onFailure(t.getMessage());
                t.printStackTrace();

            }
        });
    }
}
