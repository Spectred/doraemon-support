package com.doraemon.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Java Bean Util
 *
 * @author SWD
 */
public class BeanUtils {

    private BeanUtils() {
    }

    /**
     * Java Bean -> Map
     *
     * @param obj Entity
     * @return Map
     * @throws IntrospectionException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static Map<String, Object> bean2Map(Object obj) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        Map<String, Object> map = new HashMap<>(1 << 4);
        if (Objects.nonNull(obj)) {
            // 获取javaBean的BeanInfo对象
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass(), Object.class);
            // 获取属性描述器
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                // 获取属性名
                String key = propertyDescriptor.getName();
                // 获取该属性的值
                Method readMethod = propertyDescriptor.getReadMethod();
                // 通过反射来调用javaBean定义的getName()方法
                Object value = readMethod.invoke(obj);
                map.put(key, value);
            }
        }
        return map;
    }

}
