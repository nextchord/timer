package com.example.timer;

import android.content.Context;
import android.content.SharedPreferences;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

public class SpUtil {

    private static final String SP_FILE_NAME = "timer";

    private SpUtil() {
    }

    public static void putBoolean(Context context, String key, Boolean value) {
        SharedPreferences.Editor edit = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE).edit();
        edit.putBoolean(key, value);
        SharedPreferencesCompat.apply(edit);
    }

    public static Boolean getBoolean(Context context, String key, Boolean defValue) {
        SharedPreferences sp = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }

    public static void putFloat(Context context, String key, Float value) {
        SharedPreferences.Editor edit = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE).edit();
        edit.putFloat(key, value);
        SharedPreferencesCompat.apply(edit);
    }

    public static Float getFloat(Context context, String key, Float defValue) {
        SharedPreferences sp = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        return sp.getFloat(key, defValue);
    }

    public static void putInt(Context context, String key, Integer value) {
        SharedPreferences.Editor edit = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE).edit();
        edit.putInt(key, value);
        SharedPreferencesCompat.apply(edit);
    }

    public static Integer getInt(Context context, String key, Integer defValue) {
        SharedPreferences sp = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        return sp.getInt(key, defValue);
    }

    public static void putLong(Context context, String key, Long value) {
        SharedPreferences.Editor edit = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE).edit();
        edit.putLong(key, value);
        SharedPreferencesCompat.apply(edit);
    }

    public static Long getLong(Context context, String key, Long defValue) {
        SharedPreferences sp = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        return sp.getLong(key, defValue);
    }

    public static void putString(Context context, String key, String value) {
        SharedPreferences.Editor edit = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE).edit();
        edit.putString(key, value);
        SharedPreferencesCompat.apply(edit);
    }

    public static String getString(Context context, String key, String defValue) {
        SharedPreferences sp = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        return sp.getString(key, defValue);
    }

    public static void putStringSet(Context context, String key, Set<String> value) {
        SharedPreferences.Editor edit = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE).edit();
        edit.putStringSet(key, value);
        SharedPreferencesCompat.apply(edit);
    }

    public static Set<String> getStringSet(Context context, String key, Set<String> defValue) {
        SharedPreferences sp = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        return sp.getStringSet(key, defValue);
    }

    public static void remove(Context context, String key) {
        SharedPreferences.Editor editor = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE).edit();
        editor.remove(key);
        SharedPreferencesCompat.apply(editor);
    }

    public static void clear(Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE).edit();
        editor.clear();
        SharedPreferencesCompat.apply(editor);
    }

    public static boolean contains(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    public static Map<String, ?> getAll(Context context) {
        SharedPreferences sp = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        return sp.getAll();
    }

    public static void putBooleanInFile(Context context, String fileName, String key, Boolean value) {
        if (null == fileName || fileName.isEmpty()) fileName = SP_FILE_NAME;
        SharedPreferences.Editor edit = context.getSharedPreferences(fileName, Context.MODE_PRIVATE).edit();
        edit.putBoolean(key, value);
        SharedPreferencesCompat.apply(edit);
    }

    public static Boolean getBooleanFromFile(Context context, String fileName, String key, Boolean defValue) {
        if (null == fileName || fileName.isEmpty()) fileName = SP_FILE_NAME;
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }

    public static void putFloatInFile(Context context, String fileName, String key, Float value) {
        if (null == fileName || fileName.isEmpty()) fileName = SP_FILE_NAME;
        SharedPreferences.Editor edit = context.getSharedPreferences(fileName, Context.MODE_PRIVATE).edit();
        edit.putFloat(key, value);
        SharedPreferencesCompat.apply(edit);
    }

    public static Float getFloatFromFile(Context context, String fileName, String key, Float defValue) {
        if (null == fileName || fileName.isEmpty()) fileName = SP_FILE_NAME;
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sp.getFloat(key, defValue);
    }

    public static void putIntInFile(Context context, String fileName, String key, Integer value) {
        if (null == fileName || fileName.isEmpty()) fileName = SP_FILE_NAME;
        SharedPreferences.Editor edit = context.getSharedPreferences(fileName, Context.MODE_PRIVATE).edit();
        edit.putInt(key, value);
        SharedPreferencesCompat.apply(edit);
    }

    public static Integer getIntFromFile(Context context, String fileName, String key, Integer defValue) {
        if (null == fileName || fileName.isEmpty()) fileName = SP_FILE_NAME;
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sp.getInt(key, defValue);
    }

    public static void putLongInFile(Context context, String fileName, String key, Long value) {
        if (null == fileName || fileName.isEmpty()) fileName = SP_FILE_NAME;
        SharedPreferences.Editor edit = context.getSharedPreferences(fileName, Context.MODE_PRIVATE).edit();
        edit.putLong(key, value);
        SharedPreferencesCompat.apply(edit);
    }

    public static Long getLongFromFile(Context context, String fileName, String key, Long defValue) {
        if (null == fileName || fileName.isEmpty()) fileName = SP_FILE_NAME;
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sp.getLong(key, defValue);
    }

    public static void putStringInFile(Context context, String fileName, String key, String value) {
        if (null == fileName || fileName.isEmpty()) fileName = SP_FILE_NAME;
        SharedPreferences.Editor edit = context.getSharedPreferences(fileName, Context.MODE_PRIVATE).edit();
        edit.putString(key, value);
        SharedPreferencesCompat.apply(edit);
    }

    public static String getStringFromFile(Context context, String fileName, String key, String defValue) {
        if (null == fileName || fileName.isEmpty()) fileName = SP_FILE_NAME;
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sp.getString(key, defValue);
    }

    public static void putStringSetInFile(Context context, String fileName, String key, Set<String> value) {
        if (null == fileName || fileName.isEmpty()) fileName = SP_FILE_NAME;
        SharedPreferences.Editor edit = context.getSharedPreferences(fileName, Context.MODE_PRIVATE).edit();
        edit.putStringSet(key, value);
        SharedPreferencesCompat.apply(edit);
    }

    public static Set<String> getStringSetFromFile(Context context, String fileName, String key, Set<String> defValue) {
        if (null == fileName || fileName.isEmpty()) fileName = SP_FILE_NAME;
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sp.getStringSet(key, defValue);
    }

    public static void removeFromFile(Context context, String fileName, String key) {
        if (null == fileName || fileName.isEmpty()) fileName = SP_FILE_NAME;
        SharedPreferences.Editor editor = context.getSharedPreferences(fileName, Context.MODE_PRIVATE).edit();
        editor.remove(key);
        SharedPreferencesCompat.apply(editor);
    }

    public static void clearFromFile(Context context, String fileName) {
        if (null == fileName || fileName.isEmpty()) fileName = SP_FILE_NAME;
        SharedPreferences.Editor editor = context.getSharedPreferences(fileName, Context.MODE_PRIVATE).edit();
        editor.clear();
        SharedPreferencesCompat.apply(editor);
    }

    public static boolean containsFromFile(Context context, String fileName, String key) {
        if (null == fileName || fileName.isEmpty()) fileName = SP_FILE_NAME;
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    public static Map<String, ?> getAllFromFile(Context context, String fileName) {
        if (null == fileName || fileName.isEmpty()) fileName = SP_FILE_NAME;
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sp.getAll();
    }

    private static class SharedPreferencesCompat {

        private static final Method sApplyMethod = findApplyMethod();

        @SuppressWarnings({"unchecked", "rawtypes"})
        private static Method findApplyMethod() {
            try {
                Class clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            return null;
        }

        public static void apply(SharedPreferences.Editor editor) {
            try {
                if (sApplyMethod != null) {
                    sApplyMethod.invoke(editor);
                    return;
                }
            } catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            editor.commit();
        }
    }

}