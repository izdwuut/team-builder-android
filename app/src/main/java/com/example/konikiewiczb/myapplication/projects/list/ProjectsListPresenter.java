package com.example.konikiewiczb.myapplication.projects.list;

import com.example.konikiewiczb.myapplication.framework.IOnFinishedListener;
import com.example.konikiewiczb.myapplication.model.Repository;
import com.example.konikiewiczb.myapplication.model.User;
import com.example.konikiewiczb.myapplication.model.UserProject;

import java.util.List;

import retrofit2.Response;

public class ProjectsListPresenter implements ProjectsListContract.Presenter, IOnFinishedListener<Response<List<UserProject>>> {
    Repository<String> token;
    ProjectsListContract.View view;
    ProjectsListContract.Interactor interactor;

    public ProjectsListPresenter(ProjectsListContract.View view, Repository<String> token) {
        this.token = token;
        this.view = view;
        this.interactor = new ProjectsListInteractor(this, token);
    }
    @Override
    public void logOut() {
        token.remove();
    }

    @Override
    public void getProjectsList() {
        interactor.getProjectsList();
    }

    @Override
    public void onResponse(Response<List<UserProject>> response) {
        view.showProjectsList(response.body());
    }

    @Override
    public void onFailure(String message) {
        view.displayMessage(message);
    }
}
