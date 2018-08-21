package com.example.konikiewiczb.myapplication.framework;

import com.example.konikiewiczb.myapplication.Config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTime {
    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Config.DATE_TIME_FORMAT);

    public static String getString(LocalDateTime dateTime) {
        return dateTime.format(dateTimeFormatter);
    }
}
