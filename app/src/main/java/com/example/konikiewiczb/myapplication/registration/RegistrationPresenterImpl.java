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
        registrationView.showSuccess();
    }

    @Override
    public void onFailure() {
        registrationView.hideProgress();
        registrationView.showFailureAler();
    }

    @Override
    public void validateData(String emailAdress, String firstname, String lastname, String password, String confirmPassword) {
        boolean dataAreOk=true;

        //validate email
        if (emailAdress != null){
            if(emailAdress.equals("")){
                dataAreOk = false;
                registrationView.showEmailError();
            }
            if(!emailAdress.contains("@")){
                dataAreOk = false;
                registrationView.showEmailError();
            }
        }else{
            dataAreOk = false;
            registrationView.showEmailError();
        }

        //validate firstname
        if(firstname != null){
            if(firstname.equals("")){
                dataAreOk = false;
                registrationView.showFirstnameError();
            }
        }else{
            dataAreOk = false;
            registrationView.showEmailError();
        }

        //validate lastname
        if(lastname != null){
            if(lastname.equals("")){
                dataAreOk = false;
                registrationView.showLastnameError();
            }
        }else{
            dataAreOk = false;
            registrationView.showEmailError();
        }

        //validate password
        if(password != null){
            if(!password.equals("")){
                if(password.length()<8){
                    dataAreOk = false;
                    registrationView.showPasswordError();
                }
            }else{
                dataAreOk = false;
                registrationView.showPasswordError();
            }
        }

        //validate confirm password
        if(confirmPassword==null){
            dataAreOk = false;
            registrationView.showConfirmPasswordError();
        }
        if(!confirmPassword.equals("")){
            if(!confirmPassword.equals(password)){
                dataAreOk = false;
                registrationView.showConfirmPasswordError();
            }
        }else{
            dataAreOk = false;
            registrationView.showConfirmPasswordError();
        }

        if(dataAreOk){
            UserRegistration userRegistration = new UserRegistration(emailAdress, firstname, lastname, password, confirmPassword);
            registrationInteractor.registration(this, userRegistration);
        }
    }

    @Override
    public void onDestroy() {
        registrationView = null;
    }
}
