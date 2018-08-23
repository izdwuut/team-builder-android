package com.example.konikiewiczb.myapplication.framework.views.progress_bar;

import android.view.View;
import android.widget.ProgressBar;

public class CircleProgressBar implements ProgressBarToggler {
    private android.widget.ProgressBar progressBar;
    public CircleProgressBar(View v) {
        this.progressBar = (ProgressBar) v;
    }

    @Override
    public void show() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hide() {
        progressBar.setVisibility(View.INVISIBLE);
    }
}
