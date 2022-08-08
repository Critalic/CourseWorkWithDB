package com.example.CourseWorkWithDB.Services;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidatorFactory;
import java.util.Arrays;
import java.util.Set;

public class ValidatorService {
    private final ValidatorFactory factory; //TODO reconsider this relation

    public ValidatorService(ValidatorFactory factory) {
        this.factory = factory;
    }

    public void validateMethod(BusinessService service, String method, Object[] values) {
        Class<?>[] classes = Arrays.stream(values).map(Object::getClass).toArray(Class[]::new);
        try {
            Set<ConstraintViolation<BusinessService>> constraintViolations =
                    factory.getValidator().forExecutables()
                    .validateParameters(service, service.getClass().getMethod(method, classes), values);
            if(constraintViolations.size()!=0) {
                throw new ConstraintViolationException(constraintViolations);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
