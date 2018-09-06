package com.example.konikiewiczb.myapplication.profile;

import android.util.Log;

import com.example.konikiewiczb.myapplication.framework.http.Api;
import com.example.konikiewiczb.myapplication.framework.http.Http;
import com.example.konikiewiczb.myapplication.framework.http.RetrofitClient;
import com.example.konikiewiczb.myapplication.model.Technology;
import com.example.konikiewiczb.myapplication.model.UserTechAdd;

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
                if (Http.isCodeInRange(response.code(), 200)) {
                    onFetchingDataFinishedListener.technologiesSuccess(response);
                } else {
                    onFetchingDataFinishedListener.technologiesFailure();
                }
            }

            @Override
            public void onFailure(Call<List<Technology>> call, Throwable t) {
                onFetchingDataFinishedListener.technologiesFailure();
            }
        });
    }

    @Override
    public void deleteChosenTechnology(OnDelTechFinishedListener onDeletingTechnologyFinishedListener, String userEmail, int idTechnology) {
        Call<Void> deleteThisTechnology = RetrofitClient.get(Api.class)
                .deleteThisTechnology(userEmail, idTechnology);

        deleteThisTechnology.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (Http.isCodeInRange(response.code(), 200)) {
                    onDeletingTechnologyFinishedListener.deleteSuccess();
                    Log.d("Delete Technology", "Success. Technology: " + userEmail + " Id:" + idTechnology);
                } else {
                    onDeletingTechnologyFinishedListener.deleteFailure();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                onDeletingTechnologyFinishedListener.deleteFailure();
                Log.d("Delete Technology", "Success. Technology: " + userEmail + " Id:" + idTechnology + "\nt");
            }
        });
    }

    @Override
    public void getAllTechnologies(OnGetingAllTechnologiesFinishedListener onGetingAllTechnologiesFinishedListener) {
        Call<List<Technology>> getAllTechnologies = RetrofitClient.get(Api.class)
                .getAllTechnologies();

        getAllTechnologies.enqueue(new Callback<List<Technology>>() {
            @Override
            public void onResponse(Call<List<Technology>> call, Response<List<Technology>> response) {
                if(Http.isCodeInRange(response.code(), 200)){
                    onGetingAllTechnologiesFinishedListener.getSuccess(response);
                }else{
                    onGetingAllTechnologiesFinishedListener.getFailure();
                }
            }

            @Override
            public void onFailure(Call<List<Technology>> call, Throwable t) {
                onGetingAllTechnologiesFinishedListener.getFailure();
            }
        });
    }

    @Override
    public void addTechnologyToUser(OnAddingTechnologyToUserFinishedListener onAddingTechnologyToUserFinishedListener, String email,String technologyName, int technologyId) {
        UserTechAdd userTechAdd = new UserTechAdd(email, technologyId);
        Call<Void> addTechToUser = RetrofitClient.get(Api.class)
                .addTechToUser(userTechAdd);

        addTechToUser.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(Http.isCodeInRange(response.code(), 200)){
                    onAddingTechnologyToUserFinishedListener.addSuccess(technologyName);
                }else{
                    onAddingTechnologyToUserFinishedListener.addFailure();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                onAddingTechnologyToUserFinishedListener.addFailure();
            }
        });
    }
}
