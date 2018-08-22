package com.example.konikiewiczb.myapplication.profile;

public class TechnologyItem {
    private int technologyIcon;
    private int trashIcon;
    private String technologyName;

    public TechnologyItem(int technologyIcon, int trashIcon, String technologyName) {
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
