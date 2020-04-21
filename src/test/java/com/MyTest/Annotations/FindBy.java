package com.MyTest.Annotations;

import java.lang.annotation.*;



@Target(ElementType.FIELD)
@Repeatable(FindByList.class)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface FindBy {
    String locator();
    String itemLocator() default "";
    boolean excludeFromSearch() default false;
}

