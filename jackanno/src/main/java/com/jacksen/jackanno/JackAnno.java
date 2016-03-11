package com.jacksen.jackanno;

import android.content.Context;
import android.support.annotation.NonNull;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by jacksen on 2016/3/11.
 */
public class JackAnno {

    /**
     * @param context
     */
    public static void inject(@NonNull Context context) {
        Class clazz = context.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(ViewInject.class)) {
                ViewInject viewInject = field.getAnnotation(ViewInject.class);
                int viewId = viewInject.value();
                try {
                    Method findViewMethod = clazz.getMethod(AnnoUtil.FIND_VIEW_BY_ID, int.class);
                    Object result = findViewMethod.invoke(context, viewId);
                    boolean flag = field.isAccessible();
                    field.setAccessible(true);
                    //
                    field.set(context, result);

                    field.setAccessible(flag);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
