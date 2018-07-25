package com.example.konikiewiczb.myapplication.framework;


import com.example.konikiewiczb.myapplication.model.LoginResponse;
import com.example.konikiewiczb.myapplication.model.User;

import retrofit2.http.Body;

import retrofit2.Call;
import retrofit2.http.POST;

public interface Api {
    String BASE_URL = "https://10.0.2.2:8444/";

    @POST("login")
    Call<LoginResponse> login(@Body User user);
}
