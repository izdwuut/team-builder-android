package com.example.konikiewiczb.myapplication.framework;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SecondRetrofitClient {
    private static Retrofit retrofit = null;
    private static  Api api;

    public static Retrofit getRetrofit() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(api.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            return retrofit;
        } else {
            return retrofit;
        }
    }
}
