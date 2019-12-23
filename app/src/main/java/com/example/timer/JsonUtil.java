package com.example.timer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class JsonUtil {

    public static JSONObject toJsonObject(String text){
        return JSON.parseObject(text);

    }

    public static JSONArray toJsonArray(String text){
        return JSON.parseArray(text);
    }

    public static <T> T toObject(String text, Class<T> clazz){
        T t = JSON.parseObject(text, clazz);
        return t;
    }

    public static <T> List<T> toObjectList(String text, Class<T> clazz){
        List<T> ts = JSON.parseArray(text, clazz);
        return ts;
    }

    public static Object toJson(String text){
        return JSON.parse(text);
    }

    public static String toJSONString(Object object){
        return JSON.toJSONString(object);
    }

    public static String toJSONString(Object object, boolean prettyFormat){
        return JSON.toJSONString(object,prettyFormat);
    }

    public static Object toJson(Object javaObject){
        return JSON.toJSON(javaObject);
    }

}
