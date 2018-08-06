package com.example.konikiewiczb.myapplication.model;

public class UserRegistration {
    private String emailAddress;
    private String firstname;
    private String lastname;
    private String password;
    private String confirmPassword;

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

    public UserRegistration(String emailAddress, String password) {
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public UserRegistration(String emailAddress, String firstname, String lastname, String password, String confirmPassword) {
        this.emailAddress = emailAddress;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String toString() {
        return this.emailAddress + " " + this.firstname +" " +this.lastname+ " " +this.password + " " + this.confirmPassword;
    }
}
