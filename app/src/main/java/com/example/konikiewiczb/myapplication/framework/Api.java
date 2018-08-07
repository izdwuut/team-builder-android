package com.example.konikiewiczb.myapplication.framework;


import com.example.konikiewiczb.myapplication.model.LoginResponse;
import com.example.konikiewiczb.myapplication.model.User;
import com.example.konikiewiczb.myapplication.model.UserProject;
import com.example.konikiewiczb.myapplication.model.UserRegistration;

import java.util.List;

import retrofit2.http.Body;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {
    @POST("User/login")
    Call<String> login(@Body UserRegistration user);

    @GET("User/{email}/projects")
    Call<List<UserProject>> getUserProjects(@Path("email") String email);

    @POST("User")
    Call<Void> registerUser(@Body UserRegistration userRegistration);
}
