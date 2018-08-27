package com.example.konikiewiczb.myapplication.framework.view;

import android.app.Activity;

import com.example.konikiewiczb.myapplication.R;
import com.example.konikiewiczb.myapplication.framework.view.progress_bar.ProgressBarFactory;
import com.example.konikiewiczb.myapplication.framework.view.progress_bar.ProgressBarHandler;
import com.example.konikiewiczb.myapplication.framework.view.progress_bar.ProgressBarToggler;

public class GenericActivity extends Activity implements ProgressBarHandler {
    protected ProgressBarToggler progressBar;

    protected void init() {
        progressBar = ProgressBarFactory.getCircleProgressBar(findViewById(R.id.progress_bar));
    }

    @Override
    public ProgressBarToggler getProgressBar() {
        return progressBar;
    }
}
