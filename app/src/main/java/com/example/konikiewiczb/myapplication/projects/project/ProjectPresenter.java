package com.example.konikiewiczb.myapplication.projects.project;

import com.example.konikiewiczb.myapplication.framework.DateTime;
import com.example.konikiewiczb.myapplication.model.Project;

public class ProjectPresenter implements ProjectContract.Presenter {
    ProjectContract.Interactor interactor;
    ProjectContract.View view;
    public ProjectPresenter(ProjectContract.View view) {
        this.view = view;
        interactor = new ProjectInteractor(this);
    }

    @Override
    public void getProject(Integer id) {
        interactor.getProject(id);
    }

    @Override
    public void setProject(Project project) {
        view.showName(project.getName());
        view.showDescription(project.getDescription());
        String startDate = DateTime.getString(project.getStartDate());
        String endDate = DateTime.getString(project.getEndDate());
        view.showStartDate(startDate);
        view.showEndDate(endDate);
        view.showStatus(project.getStatus());
        view.showParticipants(project.getProjectParticipants());
        view.showEntries(project.getProjectEntries());
        view.showCandidates(project.getProjectCandidates());
        view.showTechnologies(project.getProjectTechnologies());
    }

    @Override
    public void onFailure() {
        view.showError();
    }
}
