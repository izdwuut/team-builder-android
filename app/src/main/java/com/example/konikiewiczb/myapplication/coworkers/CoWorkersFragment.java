package com.example.konikiewiczb.myapplication.coworkers;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.konikiewiczb.myapplication.R;

public class CoWorkersFragment extends Fragment implements CoWorkersContract.CoWorkersView{

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private CoWorkersContract.CoWorkersPresenter coWorkersPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle(getString(R.string.app_co_workers));
        View view = inflater.inflate(R.layout.fragment_coworkers, container, false);

        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        recyclerView = (RecyclerView) view.findViewById(R.id.rvList);

        coWorkersPresenter = new CoWorkersPresenterImpl(getActivity().getApplicationContext(), this, recyclerView);
        coWorkersPresenter.fetchWorkersList();

        return view;
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showError() {

    }
}
