package com.example.konikiewiczb.myapplication.profile;

import android.app.Dialog;

import com.example.konikiewiczb.myapplication.framework.view.progress_bar.ProgressBarHandler;
import com.example.konikiewiczb.myapplication.model.Technology;

import java.util.List;

import retrofit2.Response;

public interface ProfileContract {

    interface ProfilePresenter{

        void fetchUserTechnologies(String userEmail);

        void deleteChosenTechnology(String userEmail, int idTechnology);

        void changePassword(String email, String oldPwd, String newPwd, String cnfPwd);

        void getAllTechnologies();

        void addTechnologyToUser(String email, String technologyName, int technologyId);

    }

    interface ProfileInteractor{

        void fetchTechnologies(OnFetchingDataFinishedListener onFetchingDataFinishedListener, String userEmail);

        interface OnFetchingDataFinishedListener{

            void technologiesSuccess(Response<List<Technology>> response);

            void technologiesFailure();

        }

        void deleteChosenTechnology(OnDelTechFinishedListener onDeletingTechnologyFinishedListener, String userEmail, int idTechnology);

        interface OnDelTechFinishedListener{

            void deleteSuccess();

            void deleteFailure();

        }

        void changePassword(OnChangingPasswordFinishedListener onChangingPasswordFinishedListener, String userEmail, String oldPwd, String newPwd, String cnfPwd);

        interface OnChangingPasswordFinishedListener{

            void changeSuccess();

            void changeFailure();

        }

        void getAllTechnologies(OnGetingAllTechnologiesFinishedListener onGetingAllTechnologiesFinishedListener);

        interface OnGetingAllTechnologiesFinishedListener{

            void getSuccess(Response<List<Technology>> response);

            void getFailure();

        }

        void addTechnologyToUser(OnAddingTechnologyToUserFinishedListener onAddingTechnologyToUserFinishedListener, String email,String technologyName, int technologyId);

        interface OnAddingTechnologyToUserFinishedListener{

            void addSuccess(String technologyName);

            void addFailure();
        }

    }

    interface ProfileView extends ProgressBarHandler {

        void startAdapterDelTech(Response<List<Technology>> response);

        void startAdapterGetTech(List<Technology> technologyList);

        void closeDialog(Dialog dialog);

        void changePasswordDialog();

        void addTechSuccess(String technologyName);

        void addTechFail();

        void oldPwdEmptyError();

        void newPwdEmptyError();

        void cnfPwdEmptyError();

        void pwdDontMatchError();

        void onChangeSuccess();

        void onChangeFailure();

        void listenerTechUserList(int techListSize);
    }
}
