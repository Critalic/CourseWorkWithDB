package com.example.CourseWorkWithDB.Validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LastPairMatchValidator.class)
public @interface LastPairMatch {

    String message() default "Arguments don't match";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
