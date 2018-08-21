package com.example.konikiewiczb.myapplication.profile;

import com.example.konikiewiczb.myapplication.framework.http.Api;
import com.example.konikiewiczb.myapplication.framework.http.Http;
import com.example.konikiewiczb.myapplication.framework.http.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileInteractorImpl implements ProfileContract.ProfileInteractor {

    @Override
    public void fetchTechnologies(OnFetchingDataFinishedListener onFetchingDataFinishedListener, String userEmail) {
        Call<List<Object>> fetchUserProject = RetrofitClient.get(Api.class)
                .getUserTechnologiesList(userEmail);

        fetchUserProject.enqueue(new Callback<List<Object>>() {
            @Override
            public void onResponse(Call<List<Object>> call, Response<List<Object>> response) {
                if(Http.isCodeInRange(response.code(),200)){
                    onFetchingDataFinishedListener.TechnologiesSuccess();
                }else{
                    onFetchingDataFinishedListener.TechnologiesFailure();
                }
            }

            @Override
            public void onFailure(Call<List<Object>> call, Throwable t) {
                onFetchingDataFinishedListener.TechnologiesFailure();
            }
        });
    }
}
