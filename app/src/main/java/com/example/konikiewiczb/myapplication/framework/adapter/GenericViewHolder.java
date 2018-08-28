package com.example.konikiewiczb.myapplication.framework.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.konikiewiczb.myapplication.R;

import java.util.List;

import butterknife.ButterKnife;

public abstract class GenericViewHolder<E> extends RecyclerView.ViewHolder {
    private Dialog dialog;
    protected Context context;
    protected List<E> dataset;
    private FragmentManager fragmentManager;

    public GenericViewHolder(View v) {
        super(v);
        bind(v);
    }

    public GenericViewHolder(View v, FragmentManager fragmentManager) {
        super(v);
        this.fragmentManager = fragmentManager;
        bind(v);
    }

    public GenericViewHolder(View v, Context context, List<E> dataset) {
        super(v);
        bind(v);
        this.context = context;
        this.dataset = dataset;
    }

    protected Dialog getDialog(Integer layout) {
        if (dialog == null) {
            dialog = new Dialog(context);
            dialog.setContentView(layout);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        return dialog;
    }

    protected void bind(View v) {
        ButterKnife.bind(this, v);
    }

    protected void switchFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }
}
