package com.example.konikiewiczb.myapplication.framework.views.progress_bar;

import android.widget.ProgressBar;

public class ProgressBarFactory {
    public static ProgressBarToggler getCircleProgressBar(ProgressBar view) {
        if(view == null) {
            return null;
        }
        ProgressBarToggler progressBar = new CircleProgressBar(view);
        progressBar.hide();
        return progressBar;
    }
}
