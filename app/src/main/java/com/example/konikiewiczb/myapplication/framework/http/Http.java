package com.example.konikiewiczb.myapplication.framework.http;

public class Http {
    public static boolean isCodeInRange(int code, int range) {
        return code >= range && code < range + 100;
    }
}
