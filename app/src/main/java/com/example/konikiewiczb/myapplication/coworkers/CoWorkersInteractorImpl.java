package com.example.konikiewiczb.myapplication.coworkers;

import com.example.konikiewiczb.myapplication.framework.Api;
import com.example.konikiewiczb.myapplication.framework.RetrofitClient;
import com.example.konikiewiczb.myapplication.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoWorkersInteractorImpl implements CoWorkersContract.CoWorkersInteractor{
    @Override
    public void fetchData(OnFetchingDataFinishedListener onFetchingDataFinishedListener) {
        Call<List<User>> fetchCoworkers = RetrofitClient.get(Api.class)
                .getUserList();

        fetchCoworkers.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.code()>=200 && response.code()<300){
                    onFetchingDataFinishedListener.onSuccess(response);
                }else{
                    onFetchingDataFinishedListener.onFailure();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                onFetchingDataFinishedListener.onFailure();
                System.out.println("Failure while fetching user list from serwer\n" + t);
            }
        });

    }
}
