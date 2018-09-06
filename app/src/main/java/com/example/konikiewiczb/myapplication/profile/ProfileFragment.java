package com.example.konikiewiczb.myapplication.profile;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.konikiewiczb.myapplication.R;
import com.example.konikiewiczb.myapplication.framework.view.GenericFragment;
import com.example.konikiewiczb.myapplication.model.Technology;
import com.example.konikiewiczb.myapplication.model.User;
import com.example.konikiewiczb.myapplication.model.repositories.Repository;
import com.example.konikiewiczb.myapplication.model.repositories.UserRepository;
import com.example.konikiewiczb.myapplication.profile.adapter.AllTechnologiesAdapter;
import com.example.konikiewiczb.myapplication.profile.adapter.TechnologiesAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

public class ProfileFragment extends GenericFragment implements ProfileContract.ProfileView {

    private Repository<User> userRepository;
    private ProfileContract.ProfilePresenter profilePresenter;

    private TechnologiesAdapter technologiesAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private Dialog confirmDeleteDialog;
    private Dialog addTechnologyDialog;

    @BindView(R.id.rvTechnologies)
    RecyclerView recyclerView;
    @BindView(R.id.bAddTechnologies)
    Button addTechnology;
    @BindView(R.id.tvName)
    TextView tvFirstLastName;
    @BindView(R.id.tvEmail)
    TextView tvEmail;
    @BindView(R.id.tvRole)
    TextView tvRole;
    @BindView(R.id.tvTechListHeader)
    TextView tvTechHeader;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflate(R.layout.fragment_profile, inflater, container);
        getActivity().setTitle(getString(R.string.profil_tittle));
        ButterKnife.bind(this, view);

        userRepository = new UserRepository(getActivity().getApplicationContext());
        fetchUserData(userRepository.get());

        profilePresenter = new ProfilePresenterImpl(this);
        profilePresenter.fetchUserTechnologies(userRepository.get().getEmailAddress());

        addTechnology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profilePresenter.getAllTechnologies();
            }
        });

        return view;
    }

    void fetchUserData(User user) {
        tvFirstLastName.setText(user.toString());
        tvEmail.setText(user.getEmailAddress());
        tvRole.setText(user.getSystemRole());
    }

    @Override
    public void startAdapterDelTech(Response<List<Technology>> response) {
        confirmDeleteDialog = new Dialog(getContext());
        confirmDeleteDialog.setContentView(R.layout.dialog_delete_technology);
        confirmDeleteDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        listenerTechUserList(response.body().size());

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setHasFixedSize(true);
        technologiesAdapter = new TechnologiesAdapter(response.body(), getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(technologiesAdapter);
        technologiesAdapter.setOnTechnologyClickListener(new TechnologiesAdapter.OnItemClcikListener() {
            @Override
            public void onDeleteClick(int position) {
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
                        listenerTechUserList(response.body().size());
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
    public void addTechSuccess(String techName) {
        Toast.makeText(getContext(), getString(R.string.addedTech) + techName, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addTechFail() {
        Toast.makeText(getContext(), getString(R.string.cannotAddTech), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startAdapterGetTech(List<Technology> technologyList) {
        addTechnologyDialog = new Dialog(getContext());
        addTechnologyDialog.setContentView(R.layout.dialog_add_technology);
        addTechnologyDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button bCancelAdd = (Button) addTechnologyDialog.findViewById(R.id.bCancel);
        RecyclerView rvTechnologyList = (RecyclerView) addTechnologyDialog.findViewById(R.id.rvTechnologyList);

        layoutManager = new LinearLayoutManager(getContext());
        rvTechnologyList.setHasFixedSize(true);
        AllTechnologiesAdapter allTechnologiesAdapter = new AllTechnologiesAdapter(technologyList, getContext());
        rvTechnologyList.setLayoutManager(layoutManager);
        rvTechnologyList.setAdapter(allTechnologiesAdapter);
        allTechnologiesAdapter.setOnTechnologyClickListener(new AllTechnologiesAdapter.OnItemClcikListener() {
            @Override
            public void onAddClick(int position) {
                profilePresenter.addTechnologyToUser(userRepository.get().getEmailAddress(), technologyList.get(position).getTechnologyName(), technologyList.get(position).getId());
                listenerTechUserList(recyclerView.getAdapter().getItemCount());
            }
        });

        addTechnologyDialog.show();

        bCancelAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profilePresenter.fetchUserTechnologies(userRepository.get().getEmailAddress());
                closeDialog(addTechnologyDialog);
            }
        });

    }

    @Override
    public void listenerTechUserList(int techListSize) {
        if (techListSize == 0 ){
            tvTechHeader.setText(R.string.tech_list_empty);
        }else{
            tvTechHeader.setText(R.string.tech_list);
        }
    }

    @Override
    public void closeDialog(Dialog dialog) {
        dialog.hide();
    }
}
