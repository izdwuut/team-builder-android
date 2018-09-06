package com.example.konikiewiczb.myapplication.settings;

public class SettingsPresenterImpl implements SettingsContract.SettingsPresenter, SettingsContract.SettingsInteractor.OnChangingPasswordFinishedListener {

    private SettingsContract.SettingsView settingsView;
    private SettingsContract.SettingsInteractor settingsInteractor;

    public SettingsPresenterImpl(SettingsContract.SettingsView settingsView) {
        this.settingsView = settingsView;
        this.settingsInteractor = new SettingsInteractorImpl();
    }

    // change password

    @Override
    public void changePassword(String email, String oldPwd, String newPwd, String cnfPwd) {
        boolean pwdCanBeChangeg = true;
        if(oldPwd.isEmpty()){
            pwdCanBeChangeg = false;
            settingsView.oldPwdEmptyError();
        }

        if(newPwd.isEmpty()){
            pwdCanBeChangeg = false;
            settingsView.newPwdEmptyError();
        }

        if(cnfPwd.isEmpty()){
            pwdCanBeChangeg = false;
            settingsView.cnfPwdEmptyError();
        }

        if(pwdCanBeChangeg){
            if(!newPwd.equals(cnfPwd)){
                pwdCanBeChangeg = false;
                settingsView.pwdDontMatchError();
            }else{
                settingsInteractor.changePassword(this, email, oldPwd, newPwd, cnfPwd);
            }
        }
    }

    @Override
    public void changeSuccess() {
        settingsView.onChangeSuccess();
    }

    @Override
    public void changeFailure() {
        settingsView.onChangeFailure();
    }
}
