package com.example.konikiewiczb.myapplication.profile;

import android.util.Log;

import com.example.konikiewiczb.myapplication.framework.http.Api;
import com.example.konikiewiczb.myapplication.framework.http.Http;
import com.example.konikiewiczb.myapplication.framework.http.RetrofitClient;
import com.example.konikiewiczb.myapplication.model.Technology;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileInteractorImpl implements ProfileContract.ProfileInteractor {

    @Override
    public void fetchTechnologies(OnFetchingDataFinishedListener onFetchingDataFinishedListener, String userEmail) {
        Call<List<Technology>> fetchUserProject = RetrofitClient.get(Api.class)
                .getUserTechnologiesList(userEmail);

        fetchUserProject.enqueue(new Callback<List<Technology>>() {
            @Override
            public void onResponse(Call<List<Technology>> call, Response<List<Technology>> response) {
                if(Http.isCodeInRange(response.code(),200)){
                    onFetchingDataFinishedListener.TechnologiesSuccess(response);
                }else{
                    onFetchingDataFinishedListener.TechnologiesFailure();
                }
            }

            @Override
            public void onFailure(Call<List<Technology>> call, Throwable t) {
                onFetchingDataFinishedListener.TechnologiesFailure();
            }
        });
    }

    @Override
    public void deleteChosenTechnology(OnDeletingTechnologyFinishedListener onDeletingTechnologyFinishedListener, String userEmail, int idTechnology) {
        Call<Void> deleteThisTechnology = RetrofitClient.get(Api.class)
                .deleteThisTechnology(userEmail, idTechnology);

        deleteThisTechnology.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(Http.isCodeInRange(response.code(),200)){
                    onDeletingTechnologyFinishedListener.DeleteSuccess();
                    Log.d("Delete Technology", "Success. Technology: " + userEmail + " Id:" + idTechnology);
                }else{
                    onDeletingTechnologyFinishedListener.DeleteFailure();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                onDeletingTechnologyFinishedListener.DeleteFailure();
                Log.d("Delete Technology", "Success. Technology: " + userEmail + " Id:" + idTechnology + "\nt");
            }
        });
    }
}
