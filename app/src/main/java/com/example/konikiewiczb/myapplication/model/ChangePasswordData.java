package com.example.konikiewiczb.myapplication.model;

public class ChangePasswordData{
    String oldPassword;
    String newPassword;
    String confirmPassword;

    public ChangePasswordData(String oldPwd, String newPwd, String cnfPwd){
        this.oldPassword = oldPwd;
        this.newPassword = newPwd;
        this.confirmPassword = cnfPwd;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
