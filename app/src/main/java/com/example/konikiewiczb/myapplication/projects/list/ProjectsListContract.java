package com.example.konikiewiczb.myapplication.projects.list;

import com.example.konikiewiczb.myapplication.model.User;
import com.example.konikiewiczb.myapplication.model.UserProject;

import java.io.InputStream;
import java.util.List;

public interface ProjectsListContract {
    interface View {
         void showProjectsList(List<UserProject> projects);
         void displayMessage(String message);
    }

    interface Presenter {
        void logOut();
        void getProjectsList();
    }

    interface Interactor {
        void getProjectsList();
    }
}
