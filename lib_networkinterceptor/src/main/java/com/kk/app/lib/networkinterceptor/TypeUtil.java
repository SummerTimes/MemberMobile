package com.kk.app.lib.networkinterceptor;

import com.google.gson.internal.$Gson$Types;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author billy.qi
 * @since 16/1/6 19:09
 */
class TypeUtil {

    /**
     * Returns the type from super class's type parameter in {@link $Gson$Types#canonicalize
     * canonical form}.
     */
    static Type getSuperclassTypeParameter(Class<?> subclass, int index) {
        //先获取父类
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class) {
            //若不是父类的泛型，则取接口泛型
            Type[] genericInterfaces = subclass.getGenericInterfaces();
            for (Type type : genericInterfaces) {
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
        return $Gson$Types.canonicalize(parameterizedType.getActualTypeArguments()[index]);
    }
}
