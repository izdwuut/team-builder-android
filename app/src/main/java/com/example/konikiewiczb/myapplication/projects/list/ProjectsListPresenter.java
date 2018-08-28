package com.example.konikiewiczb.myapplication.projects.list;

import com.example.konikiewiczb.myapplication.framework.IOnFinishedListener;
import com.example.konikiewiczb.myapplication.framework.view.progress_bar.ProgressBarToggler;
import com.example.konikiewiczb.myapplication.model.User;
import com.example.konikiewiczb.myapplication.model.repositories.Repository;
import com.example.konikiewiczb.myapplication.model.UserProject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import retrofit2.Response;

public class ProjectsListPresenter implements ProjectsListContract.Presenter, IOnFinishedListener<Response<List<UserProject>>> {
    Repository<String> teamLeader;
    ProjectsListContract.View view;
    ProjectsListContract.Interactor interactor;


    public ProjectsListPresenter(ProjectsListContract.View view, Repository<User> user, Repository<String> teamLeader) {
        this.view = view;
        this.interactor = new ProjectsListInteractor(this, user);
        this.teamLeader = teamLeader;
    }

    @Override
    public void getProjectsList() {
        interactor.getProjectsList();
    }

    @Override
    public void onResponse(Response<List<UserProject>> response) {
        List<UserProject> projects = response.body();
        projects.sort(Comparator.comparing(UserProject::getName).reversed());
        List<UserProject> member = new ArrayList<>();
        List<UserProject> leader = new ArrayList<>();
        for(UserProject p : projects) {
            if(p.getRoleName().equals(teamLeader.get())) {
                leader.add(p);
            } else {
                member.add(p);
            }
        }
        view.showMemberProjectsList(member);
        view.showLeaderProjectsList(leader);
        ProgressBarToggler p = view.getProgressBar();
        p.hide();
    }

    @Override
    public void onFailure(String message) {
        view.displayMessage(message);
        view.getProgressBar().hide();
    }
}
