package com.example.konikiewiczb.myapplication.projects.list;

import com.example.konikiewiczb.myapplication.framework.Api;
import com.example.konikiewiczb.myapplication.framework.IOnFinishedListener;
import com.example.konikiewiczb.myapplication.framework.RetrofitClient;
import com.example.konikiewiczb.myapplication.model.repositories.Repository;
import com.example.konikiewiczb.myapplication.model.UserProject;

import java.io.InputStream;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectsListInteractor implements ProjectsListContract.Interactor {
    IOnFinishedListener<Response<List<UserProject>>> presenter;
    InputStream cert;
    Repository<String> token;

    public ProjectsListInteractor(IOnFinishedListener<Response<List<UserProject>>> presenter, Repository<String> token) {
        this.presenter = presenter;
        this.cert = cert;
        this.token = token;
    }

    @Override
    public void getProjectsList() {
         Call<List<UserProject>> call = RetrofitClient.get(Api.class)
                .getUserProjects(token.get());

        call.enqueue(new Callback<List<UserProject>>() {

            @Override
            public void onResponse(Call<List<UserProject>> call, Response<List<UserProject>> response) {

                System.out.println("resp: " + response.body());
                presenter.onResponse(response);
            }

            @Override
            public void onFailure(Call<List<UserProject>> call, Throwable t) {
                presenter.onFailure(t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
