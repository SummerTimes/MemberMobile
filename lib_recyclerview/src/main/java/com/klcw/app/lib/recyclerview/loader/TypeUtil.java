package com.klcw.app.lib.recyclerview.loader;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

final class TypeUtil {

    static Type getSuperclassTypeParameter(Class<?> subclass, int index) {
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class) {
            superclass = ((Class) superclass).getGenericSuperclass();
            Type[] genericInterfaces = subclass.getGenericInterfaces();
            Type[] var4 = genericInterfaces;
            int var5 = genericInterfaces.length;

            for (int var6 = 0; var6 < var5; ++var6) {
                Type type = var4[var6];
                if (!(type instanceof Class)) {
                    superclass = type;
                    break;
                }
            }

            if (superclass instanceof Class) {
                throw new RuntimeException("Missing type parameter.");
            }
        }

        ParameterizedType parameterizedType = (ParameterizedType) superclass;
        return parameterizedType.getActualTypeArguments()[index];
    }
}