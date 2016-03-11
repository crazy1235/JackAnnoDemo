package com.jacksen.jackanno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Created by jacksen on 2016/3/11.
 */
@Target(ElementType.METHOD)
public @interface OnClick {
    int[] value();
}
