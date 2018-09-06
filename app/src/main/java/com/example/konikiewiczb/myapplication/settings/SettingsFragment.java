package com.example.konikiewiczb.myapplication.settings;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.konikiewiczb.myapplication.R;
import com.example.konikiewiczb.myapplication.framework.view.GenericFragment;
import com.example.konikiewiczb.myapplication.model.User;
import com.example.konikiewiczb.myapplication.model.repositories.Repository;
import com.example.konikiewiczb.myapplication.model.repositories.UserRepository;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsFragment extends GenericFragment implements SettingsContract.SettingsView{

    private Repository<User> userRepository;
    private SettingsContract.SettingsPresenter settingsPresenter;
    private Dialog changePasswordDialog;

    @BindView(R.id.tvUserName)
    TextView userName;
    @BindView(R.id.tvUserLastname)
    TextView userLastname;
    @BindView(R.id.tvUserEmail)
    TextView userEmail;
    @BindView(R.id.bChangePwd)
    Button changePassword;
    @BindView(R.id.bChangePersonalData)
    Button changePersonalData;
    @BindView(R.id.bChangeAvatar)
    Button changeAvatar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflate(R.layout.fragment_settings, inflater, container);
        getActivity().setTitle(getString(R.string.settings));
        ButterKnife.bind(this, view);

        // fetch user data from repository

        fetchUserData();

        // set settings presenter

        settingsPresenter = new SettingsPresenterImpl(this);

        // change avatar button listener

        // change personal data button listener

        // change password button listener

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePassword();
            }
        });

        return view;
    }

    @Override
    public void changePassword() {
        changePasswordDialog = new Dialog(getContext());
        changePasswordDialog.setContentView(R.layout.dialog_change_pwd);
        changePasswordDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        changePasswordDialog.show();
        EditText tvOldPwd = (EditText) changePasswordDialog.findViewById(R.id.etOldPwd);
        EditText tvNewPwd = (EditText) changePasswordDialog.findViewById(R.id.etNewPwd);
        EditText tvCnfPwd = (EditText) changePasswordDialog.findViewById(R.id.etCnfPwd);

        Button confirmChange = (Button) changePasswordDialog.findViewById(R.id.bConfirm);
        Button cancelChange = (Button) changePasswordDialog.findViewById(R.id.bCancel);

        cancelChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeDialog(changePasswordDialog);
            }
        });

        confirmChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldPwd = tvOldPwd.getText().toString();
                String newPwd = tvNewPwd.getText().toString();
                String cnfPwd = tvCnfPwd.getText().toString();

                settingsPresenter.changePassword(userRepository.get().getEmailAddress(), oldPwd, newPwd, cnfPwd);
            }
        });
    }

    @Override
    public void oldPwdEmptyError() {
        EditText tvOldPwd = (EditText) changePasswordDialog.findViewById(R.id.etOldPwd);
        tvOldPwd.setError(getString(R.string.oldPwdDenied));
    }

    @Override
    public void newPwdEmptyError() {
        EditText tvNewPwd = (EditText) changePasswordDialog.findViewById(R.id.etNewPwd);
        tvNewPwd.setError(getString(R.string.newPwdDenied));
    }

    @Override
    public void cnfPwdEmptyError() {
        EditText tvCnfPwd = (EditText) changePasswordDialog.findViewById(R.id.etCnfPwd);
        tvCnfPwd.setError(getString(R.string.cnfPwdDenied));
    }

    @Override
    public void pwdDontMatchError() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(getString(R.string.pwdDontMatch));
        builder.setNegativeButton(getString(R.string.ok), null)
                .create()
                .show();

        EditText tvNewPwd = (EditText) changePasswordDialog.findViewById(R.id.etNewPwd);
        tvNewPwd.setError(getString(R.string.pwdDontMatch));
        EditText tvCnfPwd = (EditText) changePasswordDialog.findViewById(R.id.etCnfPwd);
        tvCnfPwd.setError(getString(R.string.pwdDontMatch));
    }

    @Override
    public void onChangeSuccess() {
        changePasswordDialog.hide();
        Toast.makeText(getContext(), getString(R.string.pwdSuccessChange), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onChangeFailure() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(getString(R.string.pwdWrong));
        builder.setNegativeButton(getString(R.string.ok), null)
                .create()
                .show();
        EditText tvOldPwd = (EditText) changePasswordDialog.findViewById(R.id.etOldPwd);
        tvOldPwd.setError(getString(R.string.oldPwdDenied));
    }

    @Override
    public void fetchUserData() {
        userRepository = new UserRepository(getActivity().getApplicationContext());
        userName.setText(userRepository.get().getFirstname());
        userLastname.setText(userRepository.get().getLastname());
        userEmail.setText(userRepository.get().getEmailAddress());
    }

    @Override
    public void closeDialog(Dialog dialog) {
        dialog.hide();
    }
}

