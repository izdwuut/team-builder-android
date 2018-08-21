package com.example.konikiewiczb.myapplication.profile;

public class ProfilePresenterImpl implements ProfileContract.ProfilePresenter, ProfileContract.ProfileInteractor.OnFetchingDataFinishedListener{

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
    public void TechnologiesSuccess() {

    }

    @Override
    public void TechnologiesFailure() {

    }
}
