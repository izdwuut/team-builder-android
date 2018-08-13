package com.example.konikiewiczb.myapplication.coworkers;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.konikiewiczb.myapplication.model.User;

import java.util.List;

import retrofit2.Response;

public class CoWorkersPresenterImpl implements CoWorkersContract.CoWorkersPresenter, CoWorkersContract.CoWorkersInteractor.OnFetchingDataFinishedListener {

    private CoWorkersContract.CoWorkersView coWorkersView;
    private CoWorkersContract.CoWorkersInteractor coWorkersInteractor;
    private RecyclerView recyclerView;
    private WorkersAdapter workersAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Context context;

    public CoWorkersPresenterImpl(Context context, CoWorkersContract.CoWorkersView coWorkersView, RecyclerView recyclerView){
        this.coWorkersView = coWorkersView;
        this.recyclerView = recyclerView;
        this.context = context;
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
        coWorkersView.hideProgressBar();
        if(response.isSuccessful()){
            for(int i = 0; i < response.body().size(); i++){
                workersAdapter = new WorkersAdapter(response.body());
                layoutManager = new LinearLayoutManager(context);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(workersAdapter);
            }
        }
    }

    @Override
    public void onFailure() {
        coWorkersView.hideProgressBar();
        coWorkersView.showError();
    }
}
