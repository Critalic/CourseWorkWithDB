package com.example.CourseWorkWithDB.DAO.JPA;

import com.example.CourseWorkWithDB.Entity.BasicEntity;
import com.example.CourseWorkWithDB.Entity.Customer;
import com.example.CourseWorkWithDB.Entity.Lot;
import com.example.CourseWorkWithDB.Entity.LotOffer;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Utils {
    public static <T extends BasicEntity> Object getValue(Field field, T identifier) {
        try {
            field.setAccessible(true);
            return field.get(identifier);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e); //TODO add exception
        }
    }

    public static BasicEntity getEntityFieldId(Field field, Object identifier, Class<? extends BasicEntity> clazz) {
        try {
            field.setAccessible(true);
            return clazz.cast(field.get(identifier));
        } catch (IllegalAccessException | ClassCastException e) {
            throw new RuntimeException(e); //TODO add exception
        }
    }

    public static <T extends BasicEntity> Predicate convertToCriteriaPredicate(Field field,
                                                                               CriteriaBuilder criteriaBuilder,
                                                                               Root<T> root, T identifier) {
        List<Type> numericalTypes = Arrays.asList(Long.class, Double.class, int.class, double.class,
                Integer.class, long.class, boolean.class, Boolean.class);
        List<Type> entityTypes = Arrays.asList(Lot.class, Customer.class, LotOffer.class);

        if (numericalTypes.contains(field.getType())) {
            return criteriaBuilder.equal(root.get(field.getName()), getValue(field, identifier));
        } else if (field.getType().equals(String.class)) {
            return criteriaBuilder.like(root.get(field.getName()), "%" + getValue(field, identifier) + "%");
        } else if (entityTypes.contains(field.getType())) {
            return criteriaBuilder.equal(root.get(field.getName()), getEntityFieldId(field, identifier,
                    field.getType().asSubclass(BasicEntity.class)).getId());
        } else {
            throw new RuntimeException("Unexpected parameter type"); //TODO add exception
        }
    }

    public static void conductInTransaction(Consumer<EntityManager> action, EntityManager entityManager) {
        try {
            entityManager.getTransaction().begin();
            action.accept(entityManager);
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            entityManager.getTransaction().rollback();
            throw e; //TODO add exception
        } finally {
            entityManager.close();
        }
    }

    public static <T extends BasicEntity> List<Predicate> convertFieldsToPredicates(T identifier,
                                                                                    CriteriaBuilder criteriaBuilder,
                                                                                    Root<T> root) {
        Field[] fields = Arrays.stream(identifier.getClass().getDeclaredFields())
                .filter(field -> Objects.nonNull(getValue(field, identifier)))
                .filter(field -> !field.getType().equals(List.class) || field.getName().equals("passwordHash"))
                .toArray(Field[]::new);

        return Arrays.stream(fields)
                .map(field -> convertToCriteriaPredicate(field, criteriaBuilder, root, identifier))
                .collect(Collectors.toList());
    }
}
