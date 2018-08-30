package com.example.konikiewiczb.myapplication.profile;

public class TechnologyDelItem {
    private int technologyIcon;
    private int trashIcon;
    private String technologyName;

    public TechnologyDelItem(int technologyIcon, int trashIcon, String technologyName) {
        this.technologyIcon = technologyIcon;
        this.trashIcon = trashIcon;
        this.technologyName = technologyName;
    }

    public int getTechnologyIcon() {
        return technologyIcon;
    }

    public String getTechnologyName() {
        return technologyName;
    }
}
