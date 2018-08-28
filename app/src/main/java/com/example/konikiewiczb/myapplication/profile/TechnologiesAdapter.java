package com.example.konikiewiczb.myapplication.profile;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.konikiewiczb.myapplication.R;
import com.example.konikiewiczb.myapplication.model.Technology;

import java.util.List;


public class TechnologiesAdapter extends RecyclerView.Adapter<TechnologiesAdapter.TechnologyViewHolder>{

    private List<Technology> technologyList;
    private OnItemClcikListener technologyListener;
    private Context context;

    public interface OnItemClcikListener{
        void onDeleteClick(int position);
    }

    public void setOnTechnologyClickListener(OnItemClcikListener listener){
        technologyListener = listener;
    }

    public static class TechnologyViewHolder extends RecyclerView.ViewHolder{
        public ImageView trashIcon;
        public ImageView technologyItem;
        public TextView technologyName;


        public TechnologyViewHolder(@NonNull View itemView, OnItemClcikListener onItemClcikListener) {
            super(itemView);
            trashIcon = itemView.findViewById(R.id.ivThrash);
            technologyItem = itemView.findViewById(R.id.ivTechnology);
            technologyName = itemView.findViewById(R.id.tvTechnologyName);

            trashIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onItemClcikListener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            onItemClcikListener.onDeleteClick(position);
                        }

                    }
                }
            });
        }
    }

    public TechnologiesAdapter(List<Technology> technologyList, Context context){
        this.technologyList = technologyList;
        this.context = context;
    }

    @NonNull
    @Override
    public TechnologyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_technology, parent, false);
        TechnologyViewHolder tvh = new TechnologyViewHolder(v, technologyListener);

        return tvh;
    }

    @Override
    public void onBindViewHolder(@NonNull TechnologyViewHolder holder, int position) {
        Technology currentItem = technologyList.get(position);

        holder.technologyName.setText(currentItem.getTechnologyName());

    }

    @Override
    public int getItemCount() {
        return technologyList == null ? 0 : technologyList.size();
    }
}
