package com.example.konikiewiczb.myapplication.model;

public class Technology {
    Integer id;
    String technologyName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTechnologyName() {
        return technologyName;
    }

    public void setTechnologyName(String technologyName) {
        this.technologyName = technologyName;
    }

    public String toString() {
        return technologyName;
    }
}
