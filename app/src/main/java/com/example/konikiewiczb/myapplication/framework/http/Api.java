package com.example.konikiewiczb.myapplication.framework.http;


import com.example.konikiewiczb.myapplication.model.ChangePasswordData;
import com.example.konikiewiczb.myapplication.model.Project;
import com.example.konikiewiczb.myapplication.model.Technology;
import com.example.konikiewiczb.myapplication.model.User;
import com.example.konikiewiczb.myapplication.model.UserProject;
import com.example.konikiewiczb.myapplication.model.UserTechAdd;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Api {
    @POST("User/login")
    Call<String> login(@Body User user);

    @POST("User/login")
    Call<Void> loginVoid(@Body User user);

    @GET("User/{email}/projects")
    Call<List<UserProject>> getUserProjects(@Path("email") String email);

    @POST("User/register")
    Call<Void> registerUser(@Body User user);

    @GET("User")
    Call<List<User>> getUserList();

    @GET("User/{email}")
    Call<User> getUser(@Path("email") String email);

    @GET("User/{email}/technologies")
    Call<List<Technology>> getUserTechnologiesList(@Path("email") String email);

    @GET("Project/{id}")
    Call<Project> getProject(@Path("id") Integer id);
    @DELETE("User/{emailAddress}/technology/{technologyId}")
    Call<Void> deleteThisTechnology(@Path("emailAddress") String email, @Path("technologyId") int id);

    @PUT("User/{emailAddress}/changePassword")
    Call<Void> changePassword(@Path("emailAddress") String email, @Body ChangePasswordData changePassword);

    @GET("Technology")
    Call<List<Technology>> getAllTechnologies();

    @POST("User/technology")
    Call<Void> addTechToUser(@Body UserTechAdd userTechAdd);
}
