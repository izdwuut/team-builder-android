package com.example.konikiewiczb.myapplication.projects.list;

import com.example.konikiewiczb.myapplication.framework.IOnFinishedListener;
import com.example.konikiewiczb.myapplication.model.Repository;
import com.example.konikiewiczb.myapplication.model.User;

import java.util.List;

import retrofit2.Response;

public class ProjectsListPresenter implements ProjectsListContract.Presenter, IOnFinishedListener<Response<List<User>>> {
    Repository<String> token;
    ProjectsListContract.View view;
    ProjectsListContract.Interactor interactor;

    public ProjectsListPresenter(ProjectsListContract.View view, Repository<String> token) {
        this.token = token;
        this.view = view;
        this.interactor = new ProjectsListInteractor(this, view.getCert());
    }
    @Override
    public void logOut() {
        token.remove();
    }

    @Override
    public void getUsersList() {
        interactor.getUsersList(token.get());
    }

    @Override
    public void onResponse(Response<List<User>> response) {
        view.showUsersList(response.body());
    }

    @Override
    public void onFailure(String message) {
        view.displayMessage(message);
    }
}
