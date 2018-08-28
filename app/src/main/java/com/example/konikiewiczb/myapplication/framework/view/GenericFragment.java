package com.example.konikiewiczb.myapplication.framework.view;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.konikiewiczb.myapplication.R;
import com.example.konikiewiczb.myapplication.framework.view.progress_bar.ProgressBarFactory;
import com.example.konikiewiczb.myapplication.framework.view.progress_bar.ProgressBarHandler;
import com.example.konikiewiczb.myapplication.framework.view.progress_bar.ProgressBarToggler;

public class GenericFragment extends Fragment implements ProgressBarHandler {
    protected ProgressBarToggler progressBar;

    protected View inflate(int resId, LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(resId, container, false);

        progressBar = ProgressBarFactory.getCircleProgressBar(view.findViewById(R.id.progress_bar));

        return view;
    }


    @Override
    public ProgressBarToggler getProgressBar() {
        return progressBar;
    }

}
