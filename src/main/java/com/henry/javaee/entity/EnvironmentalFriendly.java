package com.henry.javaee.entity;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.*;

@Retention(RUNTIME)
@Target({FIELD, METHOD, ANNOTATION_TYPE, PARAMETER})
@Documented
@Constraint(validatedBy = EnvironmentalFriendlyValidator.class)
public @interface EnvironmentalFriendly {
    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
