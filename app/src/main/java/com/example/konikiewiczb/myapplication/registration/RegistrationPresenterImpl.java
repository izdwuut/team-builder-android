package com.example.konikiewiczb.myapplication.registration;

import android.util.Log;
import android.widget.Toast;

import com.example.konikiewiczb.myapplication.model.UserRegistration;

public class RegistrationPresenterImpl implements RegistrationPresenter, RegistrationInteractor.OnRegistrationFinishedListener {

    private RegistrationView registrationView;

    private RegistrationInteractor registrationInteractor;

    public RegistrationPresenterImpl(RegistrationView registrationView){
        this.registrationView = registrationView;
        this.registrationInteractor = new RegistrationInteractorImpl();
    }
    @Override
    public void onSuccess() {
    }

    @Override
    public void onFailure() {

    }

    @Override
    public void validateData(String emailAdress, String firstname, String lastname, String password, String confirmPassword) {
        boolean dataAreOk=true;
        //validate email
        //validate firstname
        //validate lastname
        //validate password
        //validate confirm password
        UserRegistration userRegistration = new UserRegistration(emailAdress, firstname, lastname, password, confirmPassword);
        Log.d("RegistrationActivity","\n-------------------------\n" + "VALIDATING DATA" + "\n-------------------------\n");
        if(dataAreOk){
            Log.d("RegistrationActivity","\n-------------------------\n" + "SENDING DATA TO INTERACTOR" + "\n-------------------------\n");
            registrationInteractor.registration(this, userRegistration);
        }
    }

    @Override
    public void onDestroy() {

    }
}
