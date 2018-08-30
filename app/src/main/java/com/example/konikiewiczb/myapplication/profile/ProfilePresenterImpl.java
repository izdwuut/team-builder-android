package com.example.konikiewiczb.myapplication.profile;

import android.util.Log;

import com.example.konikiewiczb.myapplication.framework.view.progress_bar.ProgressBarToggler;
import com.example.konikiewiczb.myapplication.model.Technology;

import java.util.List;

import retrofit2.Response;

public class ProfilePresenterImpl implements ProfileContract.ProfilePresenter, ProfileContract.ProfileInteractor.OnFetchingDataFinishedListener, ProfileContract.ProfileInteractor.OnDelTechFinishedListener, ProfileContract.ProfileInteractor.OnChangingPasswordFinishedListener, ProfileContract.ProfileInteractor.OnGetingAllTechnologiesFinishedListener, ProfileContract.ProfileInteractor.OnAddingTechnologyToUserFinishedListener{

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
    public void technologiesSuccess(Response<List<Technology>> response) {
        if(response.isSuccessful()){
            profileView.startAdapterDelTech(response);
            progressBarToggler.hide();
        }
    }

    @Override
    public void technologiesFailure() {
        progressBarToggler.hide();
    }

    @Override
    public void deleteChosenTechnology(String userEmail, int idTechnology) {
        if(!userEmail.isEmpty()){
            profileInteractor.deleteChosenTechnology(this, userEmail, idTechnology);
        }else{
            Log.d("Delete Technology", "Failed. Technology: " + userEmail + " Id:" + idTechnology);
        }
    }

    @Override
    public void deleteSuccess() {
    }

    @Override
    public void deleteFailure() {
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
    public void changeSuccess() {
        profileView.onChangeSuccess();
    }

    @Override
    public void changeFailure() {
        profileView.onChangeFailure();
    }

    @Override
    public void getAllTechnologies() {
        profileInteractor.getAllTechnologies(this);
    }
    @Override
    public void getSuccess(Response<List<Technology>> response) {
        if(response.isSuccessful()){
            profileView.startAdapterGetTech(response.body());
        }
    }

    @Override
    public void getFailure() {

    }

    @Override
    public void addTechnologyToUser(String email, String technologyName, int technologyId) {
        profileInteractor.addTechnologyToUser(this, email, technologyName, technologyId);
    }


    @Override
    public void addSuccess(String technologyName) {
        profileView.addTechSuccess(technologyName);
    }

    @Override
    public void addFailure() {
        profileView.addTechFail();
    }
}
