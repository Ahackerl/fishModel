package com.example.fishaq.util;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author fish
 * 功能：实体类转map
 */
public class EntityUtils {


    //下面实现实体类转map
    private static List<Method> getAllMethods(Object obj) {
        List<Method> methods = new ArrayList<Method>();// 获取obj字节码
        Class<?> clazz = obj.getClass();
        while (!clazz.getName().equals("java.lang.Object")) {// 获取方法
            methods.addAll(Arrays.asList(clazz.getDeclaredMethods()));
            clazz = clazz.getSuperclass();
        }
        return methods;
    }

    //实体类转map
    public static Map<String, Object> convert2Map(Object obj) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Method> methods = getAllMethods(obj);
        for (Method m : methods) {
            String methodName = m.getName();
            if (methodName.startsWith("get")) {
                // 获取属性名
                String propertyName = methodName.substring(3).toLowerCase();
                try {
                    map.put(propertyName, m.invoke(obj));
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return map;
    }




}
