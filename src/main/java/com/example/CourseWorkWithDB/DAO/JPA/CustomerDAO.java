package com.example.CourseWorkWithDB.DAO.JPA;

import com.example.CourseWorkWithDB.Entity.Customer;
import com.example.CourseWorkWithDB.Services.Utils;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

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
//        Field[] fields = identifier.getClass().getDeclaredFields();
//
//        Arrays.stream(fields)
//            .filter(field -> Objects.nonNull(Utils.getValue(field, identifier)))
//            .map(Field::getName)
//            .colle

        return null;
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
    public void delete(Customer identifier) {

    }

    @Override
    public void update(Customer identifier) {

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
