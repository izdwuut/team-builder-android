package com.example.konikiewiczb.myapplication.framework;

public interface IOnFinishedListener<E> {
    void onResponse(E response);
    void onFailure(String message);
}
