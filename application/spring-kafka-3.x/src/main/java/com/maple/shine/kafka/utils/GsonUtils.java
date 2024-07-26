package com.maple.shine.kafka.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * GsonUtils
 *
 * @author leihz
 * @version 1.0.0
 * @since 2024/07/26 18:08
 */
public class GsonUtils {

  private static final Gson GSON = new GsonBuilder()
      .setPrettyPrinting()
      .create();


  public static String toJson(Object obj) {
    return GSON.toJson(obj);
  }

  public static <T> T toBean(String json, Class<T> clazz) {
    return GSON.fromJson(json, clazz);
  }

}
