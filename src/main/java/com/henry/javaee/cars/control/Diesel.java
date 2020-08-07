package com.henry.javaee.cars.control;

import javax.inject.Qualifier;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Qualifier()
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Diesel {
}
