package com.example.CourseWorkWithDB.Validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;

@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class LastPairMatchValidator implements ConstraintValidator<LastPairMatch, Object[]> {

    @Override
    public void initialize(LastPairMatch constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object[] passwords, ConstraintValidatorContext constraintValidatorContext) {
        return passwords[passwords.length-1].equals(passwords[passwords.length-2]);
    }
}
