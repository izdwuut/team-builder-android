package com.example.konikiewiczb.myapplication.profile;

import android.app.Dialog;
import android.util.Log;
import android.widget.Toast;

import com.example.konikiewiczb.myapplication.model.Technology;

import java.util.List;

import retrofit2.Response;

public class ProfilePresenterImpl implements ProfileContract.ProfilePresenter, ProfileContract.ProfileInteractor.OnFetchingDataFinishedListener, ProfileContract.ProfileInteractor.OnDeletingTechnologyFinishedListener{

    private ProfileContract.ProfileView profileView;
    private ProfileContract.ProfileInteractor profileInteractor;

    public ProfilePresenterImpl(ProfileContract.ProfileView profileView){
        this.profileView = profileView;
        this.profileInteractor = new ProfileInteractorImpl();
    }
    @Override
    public void fetchUserTechnologies(String userEmail) {
        profileInteractor.fetchTechnologies(this, userEmail);
    }

    @Override
    public void TechnologiesSuccess(Response<List<Technology>> response) {
        if(response.isSuccessful()){
            profileView.startAdapter(response);
            profileView.hiddeProgressBar();
        }
    }

    @Override
    public void TechnologiesFailure() {
        profileView.hiddeProgressBar();
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
}
