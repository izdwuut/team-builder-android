package com.example.konikiewiczb.myapplication.framework;


import com.example.konikiewiczb.myapplication.model.LoginResponse;
import com.example.konikiewiczb.myapplication.model.User;
import com.example.konikiewiczb.myapplication.model.UserRegistration;

import java.util.List;

import retrofit2.http.Body;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface Api {
    @POST("login")
    Call<LoginResponse> login(@Body User user);

    @GET("site/list")
    Call<List<User>> getUsersList(@Header("Authorization") String token);

    @POST("User")
    Call<Void> registerUser(@Body UserRegistration userRegistration);
}
