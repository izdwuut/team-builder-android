package com.example.konikiewiczb.myapplication.projects.project;

import com.example.konikiewiczb.myapplication.framework.ProgressBarToggler;
import com.example.konikiewiczb.myapplication.model.Project;
import com.example.konikiewiczb.myapplication.model.ProjectEntry;
import com.example.konikiewiczb.myapplication.model.Technology;
import com.example.konikiewiczb.myapplication.model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface ProjectContract {
    interface View extends ProgressBarToggler {
        void showParticipants(List<User> participants);
        void showCandidates(List<User> cands);
        void showEntries(List<ProjectEntry> entries);
        void showTechnologies(List<Technology> techs);

        void showName(String name);
        void showDescription(String description);
        void showStatus(String status);
        void showStartDate(String startDate);
        void showEndDate(String endDate);

        void showError();
    }

    interface Presenter {
        void getProject(Integer id);
        void setProject(Project project);
        void onFailure();
    }

    interface Interactor {
        void getProject(Integer id);
    }
}
