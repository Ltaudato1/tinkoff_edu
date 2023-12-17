package edu.hw10.task1;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Min {
    int min() default Integer.MIN_VALUE;
}
