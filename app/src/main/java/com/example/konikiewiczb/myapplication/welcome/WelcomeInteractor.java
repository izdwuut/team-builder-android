package com.example.konikiewiczb.myapplication.welcome;

import com.example.konikiewiczb.myapplication.framework.IOnFinishedListener;
import com.example.konikiewiczb.myapplication.framework.RetrofitClient;
import com.example.konikiewiczb.myapplication.model.User;

import java.io.InputStream;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WelcomeInteractor implements WelcomeContract.Interactor {
    IOnFinishedListener<Response<List<User>>> presenter;
    InputStream cert;

    public WelcomeInteractor(IOnFinishedListener<Response<List<User>>> presenter, InputStream cert) {
        this.presenter = presenter;
        this.cert = cert;
    }

    @Override
    public void getUsersList(String token) {
         Call<List<User>> call = RetrofitClient.getApi(cert)
                .getUsersList(token);

        call.enqueue(new Callback<List<User>>() {

            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                System.out.println("resp: " + response.body());
                presenter.onResponse(response);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                presenter.onFailure(t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
