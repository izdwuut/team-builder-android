package com.example.konikiewiczb.myapplication.framework.adapter.string_list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.konikiewiczb.myapplication.R;
import com.example.konikiewiczb.myapplication.framework.Dataset;
import com.example.konikiewiczb.myapplication.framework.adapter.GenericAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StringListAdapter<E> extends GenericAdapter<E, StringListViewHolder> implements Dataset<E> {
    @NonNull
    @Override
    public StringListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StringListViewHolder(inflate(R.layout.item_text, parent));
    }

    @Override
    public void onBindViewHolder(@NonNull StringListViewHolder holder, int position) {
        String text = dataset.get(position).toString();
        holder.text.setText(text);
    }
}
