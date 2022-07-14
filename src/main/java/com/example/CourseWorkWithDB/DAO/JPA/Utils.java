package com.example.CourseWorkWithDB.DAO.JPA;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Utils {
    public static Object getValue(Field field, Object identifier) {
        try {
            field.setAccessible(true);
            return field.get(identifier);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> Predicate convertToCriteriaPredicate(Field field, CriteriaBuilder criteriaBuilder, Root<T> root, T identifier) {
        List<Type> numericalTypes = Arrays.asList(Long.class, Double.class, int.class, double.class,
                Integer.class, long.class, boolean.class, Boolean.class);

        if (numericalTypes.stream().anyMatch(type -> type.equals(field.getType()))) {
            return criteriaBuilder.equal(root.get(field.getName()), getValue(field, identifier));
        } else if (field.getType().equals(String.class)) {
            return criteriaBuilder.like(root.get(field.getName()), "%" + getValue(field, identifier) + "%");
        } else {
            throw new RuntimeException("Unexpected parameter type");
        }
    }

    public static void conductInTransaction(Consumer<EntityManager> action, EntityManager entityManager) {
        try {
            entityManager.getTransaction().begin();
            action.accept(entityManager);
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }
}
