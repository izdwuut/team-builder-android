package com.example.konikiewiczb.myapplication.coworkers.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.konikiewiczb.myapplication.R;
import com.example.konikiewiczb.myapplication.framework.adapter.GenericViewHolder;
import com.example.konikiewiczb.myapplication.model.User;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class WorkersViewHolder extends GenericViewHolder<User> {
    @BindView(R.id.tvNameSurname)
    public TextView tvNameLastname;
    @BindView(R.id.tvEmail)
    public TextView tvEmail;

    public WorkersViewHolder(View v, Context context, List<User> dataset) {
        super(v, context, dataset);
    }

    @OnClick(R.id.worker_item_id)
    public void onClick(View view) {
        Dialog dialog = getDialog(R.layout.dialog_single_employy);
        TextView dialog_name_tv = dialog.findViewById(R.id.tvNameSurname);
        TextView dialog_email_tv = dialog.findViewById(R.id.tvEmail);
        User user = dataset.get(getAdapterPosition());
        dialog_name_tv.setText(user.toString());
        dialog_email_tv.setText(user.getEmailAddress());
        Toast.makeText(context,user.toString(),Toast.LENGTH_SHORT).show();
        dialog.show();
    }
}
