package com.example.cw.validators.annotations;

import com.example.cw.validators.classes.LastPairMatchValidator;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LastPairMatchValidator.class)
public @interface LastPairMatch {

    String message() default "Arguments don't match";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
