package com.example.konikiewiczb.myapplication.registration;

import com.example.konikiewiczb.myapplication.model.User;

public interface RegistrationInteractor {

    interface OnRegistrationFinishedListener{

        void onSuccess();

        void onFailure();
    }

    void registration(OnRegistrationFinishedListener listener, User user);
}
