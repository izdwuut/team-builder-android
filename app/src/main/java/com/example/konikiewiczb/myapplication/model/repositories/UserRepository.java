package com.example.konikiewiczb.myapplication.model.repositories;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.konikiewiczb.myapplication.Config;
import com.example.konikiewiczb.myapplication.framework.json.GsonConverter;
import com.example.konikiewiczb.myapplication.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class UserRepository implements Repository<User> {
    SharedPreferences prefs;
    String prefs_key = Config.USER_PREFERENCES;
    Gson gson;
    public UserRepository(Context context) {
        prefs = context.getSharedPreferences(prefs_key, Context.MODE_PRIVATE);
        gson = GsonConverter.get();
    }

    @Override
    public User get() {
        String user = prefs.getString(prefs_key, null);
        return gson.fromJson(user, User.class);
    }

    @Override
    public void set(User val) {
        String user = gson.toJson(val);
        prefs.edit().putString(prefs_key, user).apply();
    }

    @Override
    public void remove() {
        prefs.edit().remove(prefs_key).apply();
    }

    @Override
    public boolean isSet() {
        return prefs.contains(prefs_key);
    }
}
