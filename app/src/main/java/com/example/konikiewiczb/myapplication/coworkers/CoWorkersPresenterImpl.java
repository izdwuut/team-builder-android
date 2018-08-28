package com.example.konikiewiczb.myapplication.coworkers;

import com.example.konikiewiczb.myapplication.model.User;

import java.util.List;

import retrofit2.Response;

public class CoWorkersPresenterImpl implements CoWorkersContract.CoWorkersPresenter, CoWorkersContract.CoWorkersInteractor.OnFetchingDataFinishedListener {

    private CoWorkersContract.CoWorkersView coWorkersView;
    private CoWorkersContract.CoWorkersInteractor coWorkersInteractor;

    public CoWorkersPresenterImpl(CoWorkersContract.CoWorkersView coWorkersView){
        this.coWorkersView = coWorkersView;
        this.coWorkersInteractor = new CoWorkersInteractorImpl();
    }

    @Override
    public void fetchWorkersList() {
        coWorkersInteractor.fetchData(this);
    }

    @Override
    public void onDestroy() {
        coWorkersView = null;
    }

    @Override
    public void onSuccess(Response<List<User>> response) {
        coWorkersView.getProgressBar().show();
        if(response.isSuccessful()){
            coWorkersView.showCoWorkers(response.body());
        }
    }

    @Override
    public void onFailure() {
        coWorkersView.getProgressBar().hide();
    }
}
