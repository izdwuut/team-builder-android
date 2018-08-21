package com.example.konikiewiczb.myapplication.model;

public class ProjectEntry {
    Integer entryId;
    String positionName;

    public Integer getEntryId() {
        return entryId;
    }

    public void setEntryId(Integer entryId) {
        this.entryId = entryId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String toString() {
        return positionName;
    }
}
