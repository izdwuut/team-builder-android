package com.example.konikiewiczb.myapplication.model;

public class User {
    Integer id;
    String email;
    String password;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public User(Integer id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
