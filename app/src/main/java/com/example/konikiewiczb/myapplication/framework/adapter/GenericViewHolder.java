package com.example.konikiewiczb.myapplication.framework.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

public class GenericViewHolder extends RecyclerView.ViewHolder {
    public GenericViewHolder(View v) {
        super(v);
        ButterKnife.bind(this, v);
    }
}
