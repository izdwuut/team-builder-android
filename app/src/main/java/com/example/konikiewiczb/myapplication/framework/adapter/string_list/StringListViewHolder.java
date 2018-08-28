package com.example.konikiewiczb.myapplication.framework.adapter.string_list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.konikiewiczb.myapplication.R;
import com.example.konikiewiczb.myapplication.framework.adapter.GenericViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StringListViewHolder<E> extends GenericViewHolder<E> {
        @BindView(R.id.text_string) public TextView text;

        public StringListViewHolder(View v){
            super(v);
            ButterKnife.bind(this, v);
        }
    }
