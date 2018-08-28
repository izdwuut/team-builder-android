package com.example.konikiewiczb.myapplication.framework;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.konikiewiczb.myapplication.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StringListAdapter<T> extends RecyclerView.Adapter<StringListAdapter.ViewHolder> implements Dataset {

    List<T> dataset;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_text, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String text = dataset.get(position).toString();
        holder.text.setText(text);
    }

    @Override
    public int getItemCount() {
        return dataset == null ? 0 : dataset.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.text_string) public TextView text;

        public ViewHolder(View v){
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    public void setDataset(List dataset) {
        this.dataset = dataset;
    }
}
