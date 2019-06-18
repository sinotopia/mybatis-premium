package com.ascrud.mybatisplus.toolkit;

import com.ascrud.mybatisplus.exceptions.MybatisPlusException;

/**
 * ClassUtils
 *
 * @author Caratacus
 * @date 2017/07/08
 */
public class ClassUtils {

    /**
     * 判断是否为代理对象
     *
     * @param clazz clazz
     * @return boolean
     */
    public static boolean isProxy(Class<?> clazz) {
        if (clazz != null) {
            for (Class<?> cls : clazz.getInterfaces()) {
                String interfaceName = cls.getName();
                if (interfaceName.equals("net.sf.cglib.proxy.Factory") //cglib
                    || interfaceName.equals("org.springframework.cglib.proxy.Factory")
                    || interfaceName.equals("javassist.util.proxy.ProxyObject") //javassist
                    || interfaceName.equals("org.apache.ibatis.javassist.util.proxy.ProxyObject")) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 获取当前对象的class
     *
     * @param clazz clazz
     * @return clazz
     */
    public static Class<?> getUserClass(Class<?> clazz) {
        return isProxy(clazz) ? clazz.getSuperclass() : clazz;
    }

    /**
     * 获取当前对象的class
     *
     * @param object object
     * @return clazz
     */
    public static Class<?> getUserClass(Object object) {
        if (object == null) {
            throw new MybatisPlusException("Error: Instance must not be null");
        }
        return getUserClass(object.getClass());
    }

}
