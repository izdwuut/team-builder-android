package com.example.konikiewiczb.myapplication.registration;

import android.util.Log;

import com.example.konikiewiczb.myapplication.framework.Api;
import com.example.konikiewiczb.myapplication.framework.RetrofitClient;
import com.example.konikiewiczb.myapplication.model.UserRegistration;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationInteractorImpl implements  RegistrationInteractor{
    @Override
    public void registration(final OnRegistrationFinishedListener listener, UserRegistration userRegistration) {
        Call<Void> registrationSendUserData = RetrofitClient.get(Api.class)
                                .registerUser(userRegistration);

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
