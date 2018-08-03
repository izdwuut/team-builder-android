package com.example.konikiewiczb.myapplication.framework;

import com.example.konikiewiczb.myapplication.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SecondRetrofitClient {
    private static Retrofit retrofit = null;
    private static  Api api;

    public static Retrofit getRetrofit() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            return retrofit;
        } else {
            return retrofit;
        }
    }
}
