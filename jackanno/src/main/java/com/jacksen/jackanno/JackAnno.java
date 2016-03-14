package com.jacksen.jackanno;

import android.support.annotation.NonNull;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Created by jacksen on 2016/3/11.
 */
public class JackAnno {

    public JackAnno() {
        throw new IllegalArgumentException("JackAnno class cannot be instantiated.");
    }

    /**
     * @param context
     */
    public static void inject(@NonNull Object context) {
        HashMap<Integer, View> map = new HashMap<>();
        final Class clazz = context.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(ViewInject.class)) {
                ViewInject viewInject = field.getAnnotation(ViewInject.class);
                int viewId = viewInject.value();
                try {
                    Method findViewMethod = clazz.getMethod(AnnoUtil.FIND_VIEW_BY_ID, int.class);
                    Object result = findViewMethod.invoke(context, viewId);
                    //
                    map.put(viewId, (View) result);

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

        //
        addClickListener(context, map);
    }

    /**
     * add click listener
     *
     * @param context
     */
    private static void addClickListener(final Object context, HashMap<Integer, View> map) {
        final Class clazz = context.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (final Method method : methods) {
            if (method.isAnnotationPresent(OnClick.class)) {
                OnClick onClick = method.getAnnotation(OnClick.class);
                int[] viewIds = onClick.value();
                for (int viewId : viewIds) {
                    final View view = map.get(viewId);
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                boolean flag = method.isAccessible();
                                method.setAccessible(true);
                                method.invoke(context, view);
                                method.setAccessible(flag);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }
    }


    /**
     * for fragment
     *
     * @param object
     * @param v
     */
    public static void inject(@NonNull Object object, @NonNull View v) {
        HashMap<Integer, View> map = new HashMap<>();
        final Class clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(ViewInject.class)) {
                ViewInject viewInject = field.getAnnotation(ViewInject.class);
                int viewId = viewInject.value();
                try {
                    View view = v.findViewById(viewId);
                    //
                    map.put(viewId, (View) view);

                    boolean flag = field.isAccessible();
                    field.setAccessible(true);
                    //
                    field.set(object, view);

                    field.setAccessible(flag);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        //
        addClickListener(object, map);
    }

}
