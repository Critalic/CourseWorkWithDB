package com.example.CourseWorkWithDB.DAO.JPA;

import com.example.CourseWorkWithDB.Entity.Customer;
import com.example.CourseWorkWithDB.Services.Utils;
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class CustomerDAO implements DAO<Customer> {

    private EntityManagerFactory entityManagerFactory;

    public CustomerDAO(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public Optional<Customer> get(Long identifier) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        return Optional.of(manager.find(Customer.class, identifier));
    }

    @Override
    public List<Customer> getAll(Customer identifier) {
        EntityManager manager = entityManagerFactory.createEntityManager();

        CriteriaBuilder criteriaBuilder = entityManagerFactory.getCriteriaBuilder();
        CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
        Root<Customer> customerRoot = criteriaQuery.from(Customer.class);

        Field[] fields = Arrays.stream(identifier.getClass().getDeclaredFields())
                .filter(field -> Objects.nonNull(Utils.getValue(field, identifier)))
                .filter(field -> !field.getType().equals(List.class) || field.getName().equals("passwordHash"))
                .toArray(Field[]::new);

        List<Predicate> searchCriteria = Arrays.stream(fields)
                .map(field -> Utils.convertToCriteriaPredicate(field, criteriaBuilder, customerRoot, identifier))
                .collect(Collectors.toList());

        criteriaQuery.select(customerRoot).where(criteriaBuilder.and(searchCriteria.toArray(new Predicate[0])));
        return manager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Customer> getAll() {
        EntityManager manager = entityManagerFactory.createEntityManager();
        TypedQuery<Customer> query = manager.createQuery("select c from Customer c", Customer.class);
        return query.getResultList();
    }

    @Override
    public void save(Customer object) {
        conductInTransaction(entityManager -> entityManager.persist(object));
    }

    @Override
    public void delete(Customer object) {
        conductInTransaction(entityManager -> entityManager.remove(entityManager.merge(object)));
    }

    @Override
    public void update(Customer object) {
        conductInTransaction(entityManager -> entityManager.merge(object));
    }

    private void conductInTransaction(Consumer<EntityManager> action) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

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
