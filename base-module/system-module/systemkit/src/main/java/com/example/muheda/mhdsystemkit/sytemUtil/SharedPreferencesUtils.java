package com.example.muheda.mhdsystemkit.sytemUtil;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Created by wf
 * SharedPreferences帮助类
 *
 * 用户的信息保存在 SP_FILE_NAME_USER_PROFILE，之所以不在SP_FILE_NAME_USER_PROFILE这个文件中进行保存，
 * 是因为退出用户登录的时候，会将该file清空
 */
public class SharedPreferencesUtils {
    public static final String PHARMACY = "spref_config";

    private static SharedPreferencesUtils sharedPreferencesUtils;
    private static SharedPreferences sharedPreferences;


    private SharedPreferencesUtils() {
    }


    /**
     * 获取一个app默认的SharedPreferencesUtils
     */
//    public synchronized static SharedPreferencesUtils getInstance() {
//        sharedPreferencesUtils = getInstance(JcbApplication.getInstance().getApplicationContext());
//        return sharedPreferencesUtils;
//
//    }

    /**
     * 获取一个SharedPreferencesUtils实例,并创建SharedPreferences
     */
    public synchronized static SharedPreferencesUtils getInstance(Context context, String name) {
        if (sharedPreferencesUtils == null) {
            sharedPreferencesUtils = new SharedPreferencesUtils();
        }
        sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return sharedPreferencesUtils;

    }

    public synchronized static SharedPreferences getSharedPreferences() {

        return sharedPreferences;
    }

    /**
     * 获取一个SharedPreferencesUtils实例,并创建SharedPreferences
     * <p/>
     * 默认的都写入PHARMACY，创建该PHARMACY的sharedPreferences是放在WelcomeActivity中，其余的均可以用这个方法获取
     */
    public synchronized static SharedPreferencesUtils getInstance(Context context) {
        sharedPreferencesUtils = getInstance(context, PHARMACY);
        return sharedPreferencesUtils;
    }

    /**
     * 将传入的key和value放入到SharedPreferences中
     */
    public void putString(String key, String value) {
        if (sharedPreferences == null) {
            return;
        }
        Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * 根据传入的key和defValue从SharedPreferences中获取相应的值
     */
    public String getString(String key, String defValue) {
        if (sharedPreferences == null) {
            return defValue;
        }
        String value = sharedPreferences.getString(key, defValue);
        return value;
    }


    /**
     * 将传入的key和value放入到SharedPreferences中
     */
    public void putBoolean(String key, boolean value) {
        if (sharedPreferences == null) {
            return;
        }
        Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * 根据传入的key和defValue从SharedPreferences中获取相应的值
     */
    public boolean getBoolean(String key, boolean defValue) {
        if (sharedPreferences == null) {
            return defValue;
        }
        boolean value = sharedPreferences.getBoolean(key, defValue);
        return value;
    }


    /**
     * 将传入的key和value放入到SharedPreferences中
     */
    public void putInt(String key, int value) {
        if (sharedPreferences == null) {
            return;
        }
        Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    /**
     * 根据传入的key和defValue从SharedPreferences中获取相应的值
     */
    public int getInt(String key, int defValue) {
        if (sharedPreferences == null) {
            return defValue;
        }
        int value = sharedPreferences.getInt(key, defValue);
        return value;
    }


    /**
     * 将传入的key和value放入到SharedPreferences中
     */
    public void putLong(String key, long value) {
        if (sharedPreferences == null) {
            return;
        }
        Editor editor = sharedPreferences.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    /**
     * 根据传入的key和defValue从SharedPreferences中获取相应的值
     */
    public long getLong(String key, long defValue) {
        if (sharedPreferences == null) {
            return defValue;
        }
        long value = sharedPreferences.getLong(key, defValue);
        return value;
    }

    /**
     * 清空SharedPreferences
     */
    public void clearSharedPreferences() {
        if (sharedPreferences == null) {
            return;
        }
        Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }
}
