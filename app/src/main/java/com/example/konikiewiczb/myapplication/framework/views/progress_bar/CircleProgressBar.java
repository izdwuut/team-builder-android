package com.example.konikiewiczb.myapplication.framework.views.progress_bar;

import android.view.View;
import android.widget.ProgressBar;

public class CircleProgressBar implements ProgressBarToggler {
    private ProgressBar progressBar;
    public CircleProgressBar(ProgressBar v) {
        this.progressBar = v;
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
