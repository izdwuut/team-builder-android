package com.example.konikiewiczb.myapplication.framework.json;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import retrofit2.converter.gson.GsonConverterFactory;

public class GsonConverter{
    static Gson gson;

    private GsonConverter() {
    }

    public static Gson get() {
        if(gson == null) {
            gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
                @Override
                public LocalDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                    LocalDateTime dateTime = LocalDateTime.parse(json.getAsJsonPrimitive().getAsString());
                    return dateTime;
                }
            }).create();
        }
        return gson;
    }
}
