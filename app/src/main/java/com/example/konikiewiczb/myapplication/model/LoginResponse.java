package com.example.konikiewiczb.myapplication.model;

public class LoginResponse {
    String success;

    public String getSuccess() {
        return success;
    }

    public LoginResponse(String token) {
        success = token;
    }
}
