package com.example.konikiewiczb.myapplication.model.repositories;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.konikiewiczb.myapplication.Config;

public class TokenRepository implements Repository<String> {
    SharedPreferences prefs;
    public TokenRepository(Context context) {
        prefs = context.getSharedPreferences(Config.LOGIN_PREFERENCES, Context.MODE_PRIVATE);
    }

    @Override
    public String get() {
        return prefs.getString(Config.LOGIN_PREFERENCES_TOKEN, null);
    }

    @Override
    public void set(String val) {
        String token = val;
        prefs.edit().putString(Config.LOGIN_PREFERENCES_TOKEN, token).apply();
    }

    @Override
    public void remove() {
        prefs.edit().remove(Config.LOGIN_PREFERENCES_TOKEN).apply();
    }

    @Override
    public boolean isSet() {
        return prefs.contains(Config.LOGIN_PREFERENCES_TOKEN);
    }
}
