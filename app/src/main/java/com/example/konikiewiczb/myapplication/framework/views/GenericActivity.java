package com.example.konikiewiczb.myapplication.framework.views;

import android.app.Activity;

import com.example.konikiewiczb.myapplication.R;
import com.example.konikiewiczb.myapplication.framework.views.progress_bar.ProgressBarFactory;
import com.example.konikiewiczb.myapplication.framework.views.progress_bar.ProgressBarHandler;
import com.example.konikiewiczb.myapplication.framework.views.progress_bar.ProgressBarToggler;

public class GenericActivity extends Activity implements ProgressBarHandler {
    protected ProgressBarToggler progressBar;

    protected void init() {
        progressBar = ProgressBarFactory.getCircleProgressBar(findViewById(R.id.progress_bar_wrapper));
    }

    @Override
    public ProgressBarToggler getProgressBar() {
        return progressBar;
    }
}
