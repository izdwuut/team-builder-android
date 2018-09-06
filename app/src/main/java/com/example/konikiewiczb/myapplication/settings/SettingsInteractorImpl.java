package com.example.konikiewiczb.myapplication.settings;

import com.example.konikiewiczb.myapplication.framework.http.Api;
import com.example.konikiewiczb.myapplication.framework.http.RetrofitClient;
import com.example.konikiewiczb.myapplication.model.ChangePasswordData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingsInteractorImpl implements SettingsContract.SettingsInteractor {

    // change password

    @Override
    public void changePassword(SettingsContract.SettingsInteractor.OnChangingPasswordFinishedListener onChangingPasswordFinishedListener, String userEmail, String oldPwd, String newPwd, String cnfPwd) {
        ChangePasswordData changePasswordData = new ChangePasswordData(oldPwd, newPwd, cnfPwd);

        Call<Void> changePassword = RetrofitClient.get(Api.class)
                .changePassword(userEmail, changePasswordData);

        changePassword.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    onChangingPasswordFinishedListener.changeSuccess();
                } else {
                    onChangingPasswordFinishedListener.changeFailure();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                onChangingPasswordFinishedListener.changeFailure();
            }
        });
    }
}
