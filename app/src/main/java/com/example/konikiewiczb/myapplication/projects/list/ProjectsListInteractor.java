package com.example.konikiewiczb.myapplication.projects.list;

import com.example.konikiewiczb.myapplication.framework.http.Api;
import com.example.konikiewiczb.myapplication.framework.IOnFinishedListener;
import com.example.konikiewiczb.myapplication.framework.http.RetrofitClient;
import com.example.konikiewiczb.myapplication.model.User;
import com.example.konikiewiczb.myapplication.model.repositories.Repository;
import com.example.konikiewiczb.myapplication.model.UserProject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectsListInteractor implements ProjectsListContract.Interactor {
    IOnFinishedListener<Response<List<UserProject>>> presenter;
    Repository<User> user;

    public ProjectsListInteractor(IOnFinishedListener<Response<List<UserProject>>> presenter, Repository<User> user) {
        this.presenter = presenter;
        this.user = user;
    }

    @Override
    public void getProjectsList() {
         Call<List<UserProject>> call = RetrofitClient.get(Api.class)
                .getUserProjects(user.get().getEmailAddress());

        call.enqueue(new Callback<List<UserProject>>() {

            @Override
            public void onResponse(Call<List<UserProject>> call, Response<List<UserProject>> response) {
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
