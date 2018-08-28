package com.example.konikiewiczb.myapplication.profile;

import android.app.Dialog;

import com.example.konikiewiczb.myapplication.framework.views.progress_bar.ProgressBarHandler;
import com.example.konikiewiczb.myapplication.model.Technology;

import java.util.List;

import retrofit2.Response;

public interface ProfileContract {

    interface ProfilePresenter{

        void fetchUserTechnologies(String userEmail);

        void deleteChosenTechnology(String userEmail, int idTechnology);

        void changePassword(String email, String oldPwd, String newPwd, String cnfPwd);

    }

    interface ProfileInteractor{

        void fetchTechnologies(OnFetchingDataFinishedListener onFetchingDataFinishedListener, String userEmail);

        interface OnFetchingDataFinishedListener{

            void TechnologiesSuccess(Response<List<Technology>> response);

            void TechnologiesFailure();

        }

        void deleteChosenTechnology(OnDelTechFinishedListener onDeletingTechnologyFinishedListener, String userEmail, int idTechnology);

        interface OnDelTechFinishedListener{

            void DeleteSuccess();

            void DeleteFailure();

        }

        void changePassword(OnChangingPasswordFinishedListener onChangingPasswordFinishedListener, String userEmail, String oldPwd, String newPwd, String cnfPwd);

        interface OnChangingPasswordFinishedListener{

            void ChangeSuccess();

            void ChangeFailure();

        }

    }

    interface ProfileView extends ProgressBarHandler{

        void startAdapter(Response<List<Technology>> response);

        void closeDialog(Dialog dialog);

        void changePasswordDialog(Dialog dialog);

        void oldPwdEmptyError();

        void newPwdEmptyError();

        void cnfPwdEmptyError();

        void pwdDontMatchError();

        void onChangeSuccess();

        void onChangeFailure();
    }
}
