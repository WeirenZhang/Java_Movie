package com.weiren.zhang.library_common.ext;

import com.google.gson.Gson;

public class GsonExt {
    private static Gson gson = new Gson();

    public static <T> T fromJson(String json, Class<T> type) {
        return gson.fromJson(json, type);
    }

    public static <T> String toJson(T object) {
        return gson.toJson(object);
    }
}
