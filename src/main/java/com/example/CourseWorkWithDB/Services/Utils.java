package com.example.CourseWorkWithDB.Services;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Objects;

public class Utils {
    private Utils() {
    }

    public static String generateID(String name, String owner, String info) {
        LocalDateTime now = LocalDateTime.now();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name).append(owner).append(info).append(now);
        return stringBuilder.toString();
    }

    public static Object getValue(Field field, Object identifier) {
        try {
            field.setAccessible(true);
            return field.get(identifier);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> Predicate convertToCriteriaPredicate(Field field, CriteriaBuilder criteriaBuilder, Root<T> root, T identifier) {
        if (field.getType().equals(Long.class)) {
            return criteriaBuilder.equal(root.get(field.getName()), Utils.getValue(field, identifier));
        } else if (field.getType().equals(String.class)) {
            return criteriaBuilder.like(root.get(field.getName()), "%" + Utils.getValue(field, identifier) + "%");
        } else {
            throw new RuntimeException("Unexpected parameter type");
        }
    }
}
