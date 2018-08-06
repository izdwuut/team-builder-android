package com.example.konikiewiczb.myapplication.framework;

import com.example.konikiewiczb.myapplication.model.User;

import retrofit2.Response;

public interface IOnFinishedLoginListener {
    void onResponse(Response<String> response);
    void onFailure(String message);
}
