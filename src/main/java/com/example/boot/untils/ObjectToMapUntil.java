package com.example.boot.untils;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 实体类转map工具类
 *
 * @author ltk
 */
public class ObjectToMapUntil {

    public static Map reflect(Object object) throws Exception {
        Class clazz = object.getClass();
        List<Field> fieldList = new ArrayList<>();
        Map map = new HashMap(10);
        //循环实体类的字段
        while (clazz != null){
            Field[] fields = clazz.getDeclaredFields();
            fieldList.addAll(Arrays.asList(fields));
            clazz = clazz.getSuperclass();
        }
        //转换为HashMap
        for (Field f:fieldList) {
            f.setAccessible(true);
            map.put(f.getName(),f.get(object));
        }
        return map;
    }

}
