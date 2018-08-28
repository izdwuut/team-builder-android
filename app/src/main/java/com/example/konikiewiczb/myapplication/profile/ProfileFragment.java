package com.example.konikiewiczb.myapplication.profile;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.konikiewiczb.myapplication.R;
import com.example.konikiewiczb.myapplication.framework.views.GenericFragment;
import com.example.konikiewiczb.myapplication.model.Technology;
import com.example.konikiewiczb.myapplication.model.User;
import com.example.konikiewiczb.myapplication.model.repositories.Repository;
import com.example.konikiewiczb.myapplication.model.repositories.UserRepository;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

public class ProfileFragment extends GenericFragment implements ProfileContract.ProfileView{

    private Repository<User> userRepository;
    private ProfileContract.ProfilePresenter profilePresenter;

    private TechnologiesAdapter technologiesAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private Dialog confirmDeleteDialog;
    private Dialog changePasswordDialog;

    @BindView(R.id.rvTechnologies) RecyclerView recyclerView;
    @BindView(R.id.bChangePass) Button changePasswordBtn;
    @BindView(R.id.tvName) TextView tvFirstLastName;
    @BindView(R.id.tvEmail) TextView tvEmail;
    @BindView(R.id.tvRole) TextView tvRole;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflate(R.layout.fragment_profile,inflater,container);
        getActivity().setTitle(getString(R.string.profil_tittle));
        ButterKnife.bind(this,view);

        userRepository = new UserRepository(getActivity().getApplicationContext());
        fetchUserData(view, userRepository.get());

        profilePresenter = new ProfilePresenterImpl(this);
        profilePresenter.fetchUserTechnologies(userRepository.get().getEmailAddress());

        changePasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePasswordDialog = new Dialog(getContext());
                changePasswordDialog.setContentView(R.layout.dialog_change_pwd);
                changePasswordDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                changePasswordDialog(changePasswordDialog);
            }
        });

        return view;
    }

    void fetchUserData(View view, User user){
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
    public void changePasswordDialog(Dialog dialog) {
        dialog.show();
        EditText tvOldPwd = (EditText) dialog.findViewById(R.id.etOldPwd);
        EditText tvNewPwd = (EditText) dialog.findViewById(R.id.etNewPwd);
        EditText tvCnfPwd = (EditText) dialog.findViewById(R.id.etCnfPwd);

        Button confirmChange = (Button) dialog.findViewById(R.id.bConfirm);
        Button cancelChange = (Button) dialog.findViewById(R.id.bCancel);

        cancelChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeDialog(dialog);
            }
        });

        confirmChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldPwd = tvOldPwd.getText().toString();
                String newPwd = tvNewPwd.getText().toString();
                String cnfPwd = tvCnfPwd.getText().toString();

                profilePresenter.changePassword(userRepository.get().getEmailAddress(), oldPwd, newPwd, cnfPwd);
            }
        });
    }

    @Override
    public void oldPwdEmptyError() {
        EditText tvOldPwd = (EditText) changePasswordDialog.findViewById(R.id.etOldPwd);
        tvOldPwd.setError("Nie podałeś starego hasła!");
    }

    @Override
    public void newPwdEmptyError() {
        EditText tvNewPwd = (EditText) changePasswordDialog.findViewById(R.id.etNewPwd);
        tvNewPwd.setError("Nie podałeś nowego hasła");
    }

    @Override
    public void cnfPwdEmptyError() {
        EditText tvCnfPwd = (EditText) changePasswordDialog.findViewById(R.id.etCnfPwd);
        tvCnfPwd.setError("Nie potwierdziłeś nowego hasła");
    }

    @Override
    public void pwdDontMatchError() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Hasła są różne");
        builder.setNegativeButton("Ok", null)
                .create()
                .show();

        EditText tvNewPwd = (EditText) changePasswordDialog.findViewById(R.id.etNewPwd);
        tvNewPwd.setError("Hasła są różne");
        EditText tvCnfPwd = (EditText) changePasswordDialog.findViewById(R.id.etCnfPwd);
        tvCnfPwd.setError("Hasła są różne");
    }

    @Override
    public void onChangeSuccess() {
        changePasswordDialog.hide();
        Toast.makeText(getContext(),"Hasło zostało zmienione pomyślnie", Toast.LENGTH_LONG);
    }

    @Override
    public void onChangeFailure() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Podałeś złe hasło");
        builder.setNegativeButton("Ok", null)
                .create()
                .show();
        EditText tvOldPwd = (EditText) changePasswordDialog.findViewById(R.id.etOldPwd);
        tvOldPwd.setError("Niepoprawne hasło!");
    }
}
