package com.example.muheda.mhdsystemkit.sytemUtil;

import android.view.View;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author wangfei
 * @date 2019/4/22.
 */
public class ReflectUtils {

    public static Method getMothod(Object object,String methodName){
        try {
            Class clazz = object.getClass();
            Method declaredMethod = clazz.getDeclaredMethod(methodName, View.class);
            return declaredMethod;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void toClick(Object object, Map<Integer,Method> map, View view){
        try {
            map.get(view.getId()).invoke(object,view);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
