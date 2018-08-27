package com.example.konikiewiczb.myapplication.framework.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public abstract class GenericAdapter<E, T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> {
    protected List<E> dataset;
    protected FragmentManager fragmentManager;

    public GenericAdapter() {}

    public GenericAdapter(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public int getItemCount() {
        return dataset == null ? 0 : dataset.size();
    }

    public void setDataset(List<E> dataset) {
        this.dataset = dataset;
    }
}
