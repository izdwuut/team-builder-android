package com.example.konikiewiczb.myapplication.profile;

public class TechnologyAddItem {
    private int technologyIcon;
    private int addIcon;
    private String technologyName;

    public TechnologyAddItem(int technologyIcon, int addIcon, String technologyName) {
        this.technologyIcon = technologyIcon;
        this.addIcon = addIcon;
        this.technologyName = technologyName;
    }

    public int getTechnologyIcon() {
        return technologyIcon;
    }

    public String getTechnologyName() {
        return technologyName;
    }
}
