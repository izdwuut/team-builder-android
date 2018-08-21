package com.example.konikiewiczb.myapplication.framework.http;


import com.example.konikiewiczb.myapplication.model.UserProject;
import com.example.konikiewiczb.myapplication.model.User;

import java.util.List;

import retrofit2.http.Body;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {
    @POST("User/login")
    Call<String> login(@Body User user);

    @GET("User/{email}/projects")
    Call<List<UserProject>> getUserProjects(@Path("email") String email);

    @POST("User/register")
    Call<Void> registerUser(@Body User user);

    @GET("User")
    Call<List<User>> getUserList();
    @GET("User/{email}")
    Call<User> getUser(@Path("email") String email);
}
