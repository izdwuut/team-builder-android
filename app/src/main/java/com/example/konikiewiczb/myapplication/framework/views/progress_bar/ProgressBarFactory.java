package com.example.konikiewiczb.myapplication.framework.views.progress_bar;

import android.view.View;

import com.example.konikiewiczb.myapplication.R;

public class ProgressBarFactory {
    public static ProgressBarToggler getCircleProgressBar(View view) {
        View element = view.findViewById(R.id.progress_bar);
        if(element == null) {
            return null;
        }
        ProgressBarToggler progressBar = new CircleProgressBar(element);
        progressBar.hide();
        return progressBar;
    }
}
