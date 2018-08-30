package com.example.konikiewiczb.myapplication.model;

public class UserTechAdd {
    private String userEmailAddress;
    private int technologyId;

    public UserTechAdd(String userEmailAddress, int technologyId) {
        this.userEmailAddress = userEmailAddress;
        this.technologyId = technologyId;
    }

    public String getUserEmailAddress() {
        return userEmailAddress;
    }

    public void setUserEmailAddress(String userEmailAddress) {
        this.userEmailAddress = userEmailAddress;
    }

    public int getTechnologyId() {
        return technologyId;
    }

    public void setTechnologyId(int technologyId) {
        this.technologyId = technologyId;
    }
}
