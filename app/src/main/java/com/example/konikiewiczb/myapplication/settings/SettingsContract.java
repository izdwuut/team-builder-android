package com.example.konikiewiczb.myapplication.settings;

import android.app.Dialog;

public interface SettingsContract {

    // presenter interface

    interface SettingsPresenter{

        void changePassword(String email, String oldPwd, String newPwd, String cnfPwd);

    }

    // interactor interface

    interface SettingsInteractor{

        void changePassword(SettingsInteractor.OnChangingPasswordFinishedListener onChangingPasswordFinishedListener, String userEmail, String oldPwd, String newPwd, String cnfPwd);

        interface OnChangingPasswordFinishedListener{

            void changeSuccess();

            void changeFailure();

        }

    }

    // view interface

    interface SettingsView{

        void changePassword();

        void oldPwdEmptyError();

        void newPwdEmptyError();

        void cnfPwdEmptyError();

        void pwdDontMatchError();

        void onChangeSuccess();

        void onChangeFailure();

        void fetchUserData();

        void closeDialog(Dialog dialog);

    }

}
