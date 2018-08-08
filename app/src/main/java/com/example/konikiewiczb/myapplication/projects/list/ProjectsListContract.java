package com.example.konikiewiczb.myapplication.projects.list;

import com.example.konikiewiczb.myapplication.model.User;
import com.example.konikiewiczb.myapplication.model.UserProject;

import java.io.InputStream;
import java.util.List;

public interface ProjectsListContract {
    interface View {
         void displayMessage(String message);
         void showLeaderProjectsList(List<UserProject> projects);
         void showMemberProjectsList(List<UserProject> projects);

    }

    interface Presenter {
        void logOut();
        void getProjectsList();
    }

    interface Interactor {
        void getProjectsList();
    }
}
