package com.example.konikiewiczb.myapplication.profile;

import android.app.Dialog;

import com.example.konikiewiczb.myapplication.model.Technology;

import java.util.List;

import retrofit2.Response;

public interface ProfileContract {

    interface ProfilePresenter{

        void fetchUserTechnologies(String userEmail);

        void deleteChosenTechnology(String userEmail, int idTechnology);

    }

    interface ProfileInteractor{

        void fetchTechnologies(OnFetchingDataFinishedListener onFetchingDataFinishedListener, String userEmail);

        interface OnFetchingDataFinishedListener{

            void TechnologiesSuccess(Response<List<Technology>> response);

            void TechnologiesFailure();

        }

        void deleteChosenTechnology(OnDeletingTechnologyFinishedListener onDeletingTechnologyFinishedListener, String userEmail, int idTechnology);

        interface OnDeletingTechnologyFinishedListener{

            void DeleteSuccess();

            void DeleteFailure();

        }

    }

    interface ProfileView{

        void startAdapter(Response<List<Technology>> response);

        void closeDialog(Dialog dialog);

        void hiddeProgressBar();

        void showProgressBar();
    }
}
