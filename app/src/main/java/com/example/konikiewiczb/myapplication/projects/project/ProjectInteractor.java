package com.example.konikiewiczb.myapplication.projects.project;

import android.util.Log;

import com.example.konikiewiczb.myapplication.framework.http.Http;
import com.example.konikiewiczb.myapplication.framework.http.RetrofitClient;
import com.example.konikiewiczb.myapplication.model.Project;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectInteractor implements ProjectContract.Interactor {
    ProjectContract.Presenter presenter;

    ProjectInteractor(ProjectContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getProject(Integer id) {
        Call<Project> getProject = RetrofitClient.get().getProject(id);

        getProject.enqueue(new Callback<Project>() {
            @Override
            public void onResponse(Call<Project> call, Response<Project> response) {
                Log.d("Project response", response.body().toString());
                if(Http.isCodeInRange(response.code(),200)){
                    presenter.setProject(response.body());

                } else {
                    presenter.onFailure();
                }
            }

            @Override
            public void onFailure(Call<Project> call, Throwable t) {
                t.printStackTrace();
                presenter.onFailure();
            }
        });
    }
}
