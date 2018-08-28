package com.example.konikiewiczb.myapplication.profile;

import android.util.Log;

import com.example.konikiewiczb.myapplication.framework.view.progress_bar.ProgressBarToggler;
import com.example.konikiewiczb.myapplication.model.Technology;

import java.util.List;

import retrofit2.Response;

public class ProfilePresenterImpl implements ProfileContract.ProfilePresenter, ProfileContract.ProfileInteractor.OnFetchingDataFinishedListener, ProfileContract.ProfileInteractor.OnDelTechFinishedListener, ProfileContract.ProfileInteractor.OnChangingPasswordFinishedListener{

    private ProfileContract.ProfileView profileView;
    private ProfileContract.ProfileInteractor profileInteractor;
    private ProgressBarToggler progressBarToggler;

    public ProfilePresenterImpl(ProfileContract.ProfileView profileView){
        this.profileView = profileView;
        this.profileInteractor = new ProfileInteractorImpl();
        progressBarToggler = profileView.getProgressBar();
    }
    @Override
    public void fetchUserTechnologies(String userEmail) {
        profileInteractor.fetchTechnologies(this, userEmail);
    }

    @Override
    public void TechnologiesSuccess(Response<List<Technology>> response) {
        if(response.isSuccessful()){
            profileView.startAdapter(response);
            progressBarToggler.hide();
        }
    }

    @Override
    public void TechnologiesFailure() {
        progressBarToggler.hide();
    }

    @Override
    public void deleteChosenTechnology(String userEmail, int idTechnology) {
        if(!userEmail.isEmpty()){
            profileInteractor.deleteChosenTechnology(this, userEmail, idTechnology);
            Log.d("Delete Technology", "Failed. Technology: " + userEmail + " Id:" + idTechnology);
        }else{
            Log.d("Delete Technology", "Failed. Technology: " + userEmail + " Id:" + idTechnology);
        }
    }

    @Override
    public void DeleteSuccess() {
    }

    @Override
    public void DeleteFailure() {
    }

    @Override
    public void changePassword(String email, String oldPwd, String newPwd, String cnfPwd) {
        boolean pwdCanBeChangeg = true;
        if(oldPwd.isEmpty()){
            pwdCanBeChangeg = false;
            profileView.oldPwdEmptyError();
        }

        if(newPwd.isEmpty()){
            pwdCanBeChangeg = false;
            profileView.newPwdEmptyError();
        }

        if(cnfPwd.isEmpty()){
            pwdCanBeChangeg = false;
            profileView.cnfPwdEmptyError();
        }

        if(pwdCanBeChangeg){
            if(!newPwd.equals(cnfPwd)){
                pwdCanBeChangeg = false;
                profileView.pwdDontMatchError();
            }else{
                profileInteractor.changePassword(this, email, oldPwd, newPwd, cnfPwd);
            }
        }
    }

    @Override
    public void ChangeSuccess() {
        profileView.onChangeSuccess();
    }

    @Override
    public void ChangeFailure() {
        profileView.onChangeFailure();
    }
}
