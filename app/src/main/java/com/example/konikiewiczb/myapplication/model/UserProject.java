package com.example.konikiewiczb.myapplication.model;

public class UserProject {
    String name;
    String description;
    String roleName;
    String positionName;

    public UserProject(String name, String description, String roleName, String positionName) {

        this.name = name;
        this.description = description;
        this.roleName = roleName;
        this.positionName = positionName;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

}
