package com.example.fishaq.util;


import com.fasterxml.jackson.databind.ObjectMapper;
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
 * 功能：实体类转map 实体类转JSONObject JSONObject转实体类
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

    //实体类转JSONObject
    public static JSONObject convert2JsonObject(Object obj) {
        JSONObject jsonObject=new JSONObject();
        List<Method> methods = getAllMethods(obj);
        for (Method m : methods) {
            String methodName = m.getName();
            if (methodName.startsWith("get")) {
                // 获取属性名
                String propertyName = methodName.substring(3).toLowerCase();
                try {
                    jsonObject.put(propertyName, m.invoke(obj));
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return jsonObject;
    }


    //JSONObject转实体类
    public static<T> Object jsonObject2Obj(JSONObject jsonObject,Class<T> obj) throws Exception {
        T t = null;

        ObjectMapper objectMapper = new ObjectMapper();
        t = objectMapper.readValue(jsonObject.toString(), obj);

        return t;
    }






}
