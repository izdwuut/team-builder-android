package com.example.konikiewiczb.myapplication.model.repositories;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.konikiewiczb.myapplication.Config;
import com.example.konikiewiczb.myapplication.framework.json.GsonConverter;
import com.example.konikiewiczb.myapplication.model.User;
import com.google.gson.Gson;

public class UserRepository implements Repository<User> {
    SharedPreferences prefs;
    String prefs_key = Config.USER_PREFERENCES;
    Gson gson;
    static User user;
    public UserRepository(Context context) {
        prefs = context.getSharedPreferences(prefs_key, Context.MODE_PRIVATE);
        gson = GsonConverter.get();
    }

    @Override
    public User get() {
        if(user == null) {
            String user = prefs.getString(prefs_key, null);
            this.user = gson.fromJson(user, User.class);
        }
        return user;
    }

    @Override
    public void set(User val) {
        String user = gson.toJson(val);
        prefs.edit().putString(prefs_key, user).apply();
        this.user = val;
    }

    @Override
    public void remove() {
        prefs.edit().remove(prefs_key).apply();
        this.user = null;
    }

    @Override
    public boolean isSet() {
        return prefs.contains(prefs_key);
    }
}
