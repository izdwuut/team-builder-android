package com.example.konikiewiczb.myapplication.projects.list;

import com.example.konikiewiczb.myapplication.framework.view.progress_bar.ProgressBarHandler;
import com.example.konikiewiczb.myapplication.model.UserProject;

import java.util.List;

public interface ProjectsListContract {
    interface View extends ProgressBarHandler {
         void displayMessage(String message);
         void showLeaderProjectsList(List<UserProject> projects);
         void showMemberProjectsList(List<UserProject> projects);
    }

    interface Presenter {
        void getProjectsList();
    }

    interface Interactor {
        void getProjectsList();
    }
}
