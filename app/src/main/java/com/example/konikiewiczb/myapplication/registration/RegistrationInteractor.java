package com.example.konikiewiczb.myapplication.registration;

import com.example.konikiewiczb.myapplication.model.UserRegistration;

public interface RegistrationInteractor {

    interface OnRegistrationFinishedListener{

        void onSuccess();

        void onFailure();
    }

    void registration(OnRegistrationFinishedListener listener, UserRegistration userRegistration);
}
