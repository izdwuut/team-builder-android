package com.example.konikiewiczb.myapplication.registration;

import android.util.Log;

import com.example.konikiewiczb.myapplication.framework.Api;
import com.example.konikiewiczb.myapplication.framework.RetrofitClient;
import com.example.konikiewiczb.myapplication.framework.SecondRetrofitClient;
import com.example.konikiewiczb.myapplication.model.UserRegistration;

import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationInteractorImpl implements  RegistrationInteractor{
    InputStream cert;
    @Override
    public void registration(final OnRegistrationFinishedListener listener, UserRegistration userRegistration) {
        Log.d("RegistrationActivity","\n-------------------------\n" + "CALL" + "\n-------------------------\n");
        Call<Void> registrationSendUserData = SecondRetrofitClient.getRetrofit()
                .create(Api.class)
                .registerUser(userRegistration);
        registrationSendUserData.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("RegistrationActivity","\n-------------------------\n" + response.code() + "\n-------------------------\n");
                if(response.code()>=200 && response.code()<300){
                    listener.onSuccess();
                }else{
                    listener.onFailure();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("RegistrationActivity","\n\n" + t);
                listener.onFailure();
            }
        });
    }
}
