package com.example.konikiewiczb.myapplication.coworkers;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.konikiewiczb.myapplication.R;
import com.example.konikiewiczb.myapplication.coworkers.adapter.WorkersAdapter;
import com.example.konikiewiczb.myapplication.framework.view.GenericFragment;
import com.example.konikiewiczb.myapplication.model.User;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CoWorkersFragment extends GenericFragment implements CoWorkersContract.CoWorkersView{
    @BindView(R.id.rvList) RecyclerView coWorkers;
    private CoWorkersContract.CoWorkersPresenter coWorkersPresenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle(getString(R.string.app_co_workers));
        View view = inflate(R.layout.fragment_coworkers, inflater, container);
        ButterKnife.bind(this, view);

        Context context = getContext();
        coWorkers.setAdapter(new WorkersAdapter(context));
        coWorkers.setLayoutManager(new LinearLayoutManager(context));
        coWorkersPresenter = new CoWorkersPresenterImpl(this);
        coWorkersPresenter.fetchWorkersList();

        return view;
    }

    public void showCoWorkers(List<User> dataset) {
        WorkersAdapter adapter = (WorkersAdapter) coWorkers.getAdapter();
        adapter.setDataset(dataset);
        adapter.notifyDataSetChanged();
    }
}
