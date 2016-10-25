package com.wix.invoke;

import org.apache.commons.lang3.reflect.MethodUtils;

import java.lang.reflect.Method;

/**
 * Created by rotemm on 26/10/2016.
 */

public class MethodUtilsExt extends MethodUtils {

    public static Object invokeMethodEvenIfInaccessible(Object object, String methodName) throws Exception {
        Method method = object.getClass().getDeclaredMethod(methodName);
        method.setAccessible(true);
        return method.invoke(object);
    }
}
