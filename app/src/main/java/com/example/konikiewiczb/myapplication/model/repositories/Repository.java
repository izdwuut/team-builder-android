package com.example.konikiewiczb.myapplication.model.repositories;

public interface Repository<T> {
    T get();
    void set(T val);
    void remove();
    boolean isSet();
}
