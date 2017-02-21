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

    private ClassUtil() {
    }

    /**
     * 获取类的所有属性，包括
     *
     * @param cls
     * @return
     */
    public static Field[] getAllField(Class<?> cls) {

        List<Field> publicFields = Arrays.asList(cls.getFields());
        List<Field> declaredFields = Arrays.asList(cls.getDeclaredFields());

        List<Field> allFields = new ArrayList<>();
        allFields.addAll(publicFields);

        for (Field field : declaredFields) {
            if (!allFields.contains(field)) {
                allFields.add(field);
            }
        }
        return allFields.toArray(new Field[0]);
    }
}
