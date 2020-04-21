package com.MyTest.Annotations;



import com.MyTest.MyControlObject.Control;

import java.lang.annotation.*;


@Target(ElementType.FIELD)
@Repeatable(SubItems.class)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface SubItem {
    String name();
    String locator();
    Class<? extends Control> controlType() default Control.class;
}
