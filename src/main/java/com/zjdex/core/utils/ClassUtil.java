package com.zjdex.core.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 类信息操作工具
 * 
 * @author LIUAOZ
 * @version 1.0
 */
public class ClassUtil {

    /**
     * 获取类的所有属性，包括
     * 
     * @param cls
     * @return
     */
    public static Field[] getAllField(Class<?> cls) {

        List<Field> public_fields = Arrays.asList(cls.getFields());
        List<Field> declared_fields = Arrays.asList(cls.getDeclaredFields());

        List<Field> all_fields = new ArrayList<>();
        all_fields.addAll(public_fields);

        for (Field field : declared_fields) {
            if (!all_fields.contains(field)) {
                all_fields.add(field);
            }
        }

        return all_fields.toArray(new Field[0]);
    }

}
