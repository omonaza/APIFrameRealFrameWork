package com.devxschool.summer.utility;

import com.google.gson.Gson;

import java.util.List;

public class ObjectConverter {

    private static Gson gson = new Gson();

    public static <T> String convertObjectToJson(T object) {
        return gson.toJson(object);
    }

    public static <T> T convertJsonToObject(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }
}
