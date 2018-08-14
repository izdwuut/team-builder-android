package com.example.konikiewiczb.myapplication.framework.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonConverter{
    static Gson gson;

    private GsonConverter() {
    }

    public static Gson get() {
        if(gson == null) {
            gson = new GsonBuilder().create();
        }
        return gson;
    }
}
