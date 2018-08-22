package com.example.konikiewiczb.myapplication.coworkers;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.konikiewiczb.myapplication.R;
import com.example.konikiewiczb.myapplication.model.User;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

public class CoWorkersFragment extends Fragment implements CoWorkersContract.CoWorkersView{

//    @BindView(R.id.progressBar) ProgressBar progressBar;
    @BindView(R.id.rvList) RecyclerView recyclerView;
    private CoWorkersContract.CoWorkersPresenter coWorkersPresenter;
    private WorkersAdapter workersAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private FragmentManager fragmentManager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle(getString(R.string.app_co_workers));
        View view = inflater.inflate(R.layout.fragment_coworkers, container, false);
        ButterKnife.bind(this, view);

        coWorkersPresenter = new CoWorkersPresenterImpl(this);
        coWorkersPresenter.fetchWorkersList();

        return view;
    }

    @Override
    public void showProgressBar() {
//        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {

//        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showError() {

    }

    @Override
    public void adapterThisShit(Response<List<User>> response) {
        for(int i = 0; i < response.body().size(); i++){
            workersAdapter = new WorkersAdapter(response.body(),getContext());
            layoutManager = new LinearLayoutManager(getContext());
            fragmentManager = getFragmentManager();
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(workersAdapter);
            workersAdapter.setOnItemClickListener(new WorkersAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Toast.makeText(getContext(), response.body().get(position).getEmailAddress(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
