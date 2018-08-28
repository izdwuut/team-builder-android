package com.example.konikiewiczb.myapplication.framework.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.konikiewiczb.myapplication.framework.Dataset;

import java.util.List;

public abstract class GenericAdapter<E, T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> implements Dataset<E> {
    protected List<E> dataset;
    protected FragmentManager fragmentManager;
    protected Context context;
    public GenericAdapter() {}

    public GenericAdapter(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public GenericAdapter(Context context) {
        this.context = context;
    }

    public int getItemCount() {
        return dataset == null ? 0 : dataset.size();
    }

    public void setDataset(List<E> dataset) {
        this.dataset = dataset;
    }

    protected View inflate(Integer layout, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(layout, parent, false);
        return view;
    }
}
