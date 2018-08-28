package com.example.konikiewiczb.myapplication.framework.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.ButterKnife;

public abstract class GenericViewHolder<E> extends RecyclerView.ViewHolder {
    private Dialog dialog;
    protected Context context;
    protected List<E> dataset;
    public GenericViewHolder(View v) {
        super(v);
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
}
