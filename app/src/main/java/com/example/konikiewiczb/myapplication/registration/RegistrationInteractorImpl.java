package com.example.konikiewiczb.myapplication.registration;

import com.example.konikiewiczb.myapplication.framework.http.Api;
import com.example.konikiewiczb.myapplication.framework.http.RetrofitClient;
import com.example.konikiewiczb.myapplication.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationInteractorImpl implements  RegistrationInteractor{
    @Override
    public void registration(final OnRegistrationFinishedListener listener, User user) {
        Call<Void> registrationSendUserData = RetrofitClient.get(Api.class)
                                .registerUser(user);

        registrationSendUserData.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.code()>=200 && response.code()<300){
                    listener.onSuccess();
                }else{
                    listener.onFailure();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
                listener.onFailure();
            }
        });
    }
}
