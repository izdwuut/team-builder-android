package com.example.konikiewiczb.myapplication.coworkers;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.konikiewiczb.myapplication.ProfileFragment;
import com.example.konikiewiczb.myapplication.R;
import com.example.konikiewiczb.myapplication.model.User;
import com.example.konikiewiczb.myapplication.single.employee.SingleEmployeeFragment;

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
                workersAdapter.setOnItemClickListener(new WorkersAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClcik(int position) {
                        Toast.makeText(context, response.body().get(position).getEmailAddress(),Toast.LENGTH_SHORT).show();
//                        Fragment fragment = new SingleEmployeeFragment();
//                        AppCompatActivity activity = (AppCompatActivity) context.getApplicationContext();
//                        activity.getSupportFragmentManager().beginTransaction()
//                                .replace(R.id.fragment_container, fragment)
//                                .addToBackStack(null)
//                                .commit();
//                        response.body().get(position).setFirstname("CLICKED ELO");
//                        response.body().get(position).setLastname("CLICKED ELO");
//                        workersAdapter.notifyItemChanged(position);
                    }
                });
            }
        }
    }

    @Override
    public void onFailure() {
        coWorkersView.hideProgressBar();
        coWorkersView.showError();
    }
}
