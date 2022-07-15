package com.example.CourseWorkWithDB.DAO.JPA.Implementations;

import com.example.CourseWorkWithDB.DAO.JPA.DAO;
import com.example.CourseWorkWithDB.Entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

import static com.example.CourseWorkWithDB.DAO.JPA.Utils.*;

public class CustomerDAO implements DAO<Customer> {

    private final EntityManagerFactory entityManagerFactory;

    public CustomerDAO(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public Optional<Customer> get(Long identifier) {
        return Optional.of(entityManagerFactory.createEntityManager().find(Customer.class, identifier));
    }

    @Override
    public List<Customer> getAll(Customer identifier) {
        EntityManager manager = entityManagerFactory.createEntityManager();

        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
        Root<Customer> customerRoot = criteriaQuery.from(Customer.class);

        List<Predicate> searchCriteria = convertFieldsToPredicates(identifier, criteriaBuilder, customerRoot);

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
        conductInTransaction(entityManager -> entityManager.persist(object), entityManagerFactory.createEntityManager());
    }

    @Override
    public void delete(Customer object) {
        conductInTransaction(entityManager -> entityManager.remove(entityManager.merge(object)),
                entityManagerFactory.createEntityManager());
    }

    @Override
    public void update(Customer object) {
        conductInTransaction(entityManager -> entityManager.merge(object), entityManagerFactory.createEntityManager());
    }
}
