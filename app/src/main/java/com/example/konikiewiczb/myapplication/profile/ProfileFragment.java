package com.example.konikiewiczb.myapplication.profile;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.konikiewiczb.myapplication.R;
import com.example.konikiewiczb.myapplication.model.Technology;
import com.example.konikiewiczb.myapplication.model.User;
import com.example.konikiewiczb.myapplication.model.repositories.Repository;
import com.example.konikiewiczb.myapplication.model.repositories.UserRepository;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

public class ProfileFragment extends Fragment implements ProfileContract.ProfileView{

    private Repository<User> userRepository;
    private ProfileContract.ProfilePresenter profilePresenter;
    private RecyclerView recyclerView;
    private TechnologiesAdapter technologiesAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Dialog confirmDeleteDialog;
    @BindView(R.id.pbTechnology) ProgressBar progressBar;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        getActivity().setTitle(getString(R.string.profil_tittle));
        ButterKnife.bind(this, view);

        recyclerView = view.findViewById(R.id.rvTechnologies);

        userRepository = new UserRepository(getActivity().getApplicationContext());
        fetchUserData(view, userRepository.get());

        profilePresenter = new ProfilePresenterImpl(this);
        profilePresenter.fetchUserTechnologies(userRepository.get().getEmailAddress());

        return view;
    }

    void fetchUserData(View view, User user){
        TextView tvFirstLastName = view.findViewById(R.id.tvName);
        TextView tvEmail = view.findViewById(R.id.tvEmail);
        TextView tvRole = view.findViewById(R.id.tvRole);
        tvFirstLastName.setText(user.toString());
        tvEmail.setText(user.getEmailAddress());
        tvRole.setText(user.getSystemRole());
    }

    @Override
    public void startAdapter(Response<List<Technology>> response) {

        confirmDeleteDialog = new Dialog(getContext());
        confirmDeleteDialog.setContentView(R.layout.dialog_delete_technology);
        confirmDeleteDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setHasFixedSize(true);
        technologiesAdapter = new TechnologiesAdapter(response.body(), getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(technologiesAdapter);
        technologiesAdapter.setOnTechnologyClickListener(new TechnologiesAdapter.OnItemClcikListener() {
            @Override
            public void onDeleteClick(int position) {
                Toast.makeText(getContext(), "Delete click " + response.body().get(position).getId() ,Toast.LENGTH_SHORT).show();

                TextView dialog_technology_name = (TextView) confirmDeleteDialog.findViewById(R.id.TechnologyName);
                dialog_technology_name.setText(response.body().get(position).getTechnologyName());
                confirmDeleteDialog.show();

                Button confirmDelete = (Button) confirmDeleteDialog.findViewById(R.id.bConfirm);
                confirmDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        profilePresenter.deleteChosenTechnology(userRepository.get().getEmailAddress(), response.body().get(position).getId());
                        response.body().remove(position);
                        technologiesAdapter.notifyDataSetChanged();
                        closeDialog(confirmDeleteDialog);
                    }
                });
                Button cancelDelete = (Button) confirmDeleteDialog.findViewById(R.id.bCancel);
                cancelDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        closeDialog(confirmDeleteDialog);
                    }
                });

            }
        });
    }

    @Override
    public void closeDialog(Dialog dialog) {
        dialog.hide();
    }

    @Override
    public void hiddeProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }
}
