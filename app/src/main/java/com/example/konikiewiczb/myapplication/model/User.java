package com.example.konikiewiczb.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {
    Integer id;
    @SerializedName(value="emailAddress", alternate={"EmailAddress"})
    private String emailAddress;
    private String firstname;
    private String lastname;
    private String password;
    private String confirmPassword;
    private List<Technology> userTechnologies;
    String systemRole;
    String status;

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    String positionName;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public User(String emailAddress, String password) {
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public User(String emailAddress, String firstname, String lastname, String password, String confirmPassword) {
        this.emailAddress = emailAddress;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSystemRole() {
        return systemRole;
    }

    public void setSystemRole(String systemRole) {
        this.systemRole = systemRole;
    }

    public List<Technology> getUserTechnologies() {
        return userTechnologies;
    }

    public void setUserTechnologies(List<Technology> userTechnologies) {
        this.userTechnologies = userTechnologies;
    }

    public String toString() {
        return firstname + ' ' + lastname;
    }
}
