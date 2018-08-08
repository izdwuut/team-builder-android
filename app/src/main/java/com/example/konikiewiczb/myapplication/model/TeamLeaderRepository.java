package com.example.konikiewiczb.myapplication.model;

import android.content.Context;

import com.example.konikiewiczb.myapplication.R;

public class TeamLeaderRepository implements Repository<String> {

    String teamLeader;

    public TeamLeaderRepository(Context context) {
        this.teamLeader = context.getString(R.string.leader_role);
    }

    @Override
    public String get() {
        return teamLeader;
    }

    @Override
    public void set(String val) {
        teamLeader = val;
    }

    @Override
    public void remove() {
        teamLeader = null;
    }

    @Override
    public boolean isSet() {
        return teamLeader == null;
    }
}
