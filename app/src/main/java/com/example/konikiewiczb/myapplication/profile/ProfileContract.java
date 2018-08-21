package com.example.konikiewiczb.myapplication.profile;

public interface ProfileContract {

    interface ProfilePresenter{

        void fetchUserTechnologies(String userEmail);

    }

    interface ProfileInteractor{

        interface OnFetchingDataFinishedListener{

            void TechnologiesSuccess();

            void TechnologiesFailure();

        }

        void fetchTechnologies(OnFetchingDataFinishedListener onFetchingDataFinishedListener, String userEmail);


    }

    interface ProfileView{

    }
}
