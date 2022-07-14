package com.example.CourseWorkWithDB.DAO.JPA;

import com.example.CourseWorkWithDB.Entity.Lot;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.CourseWorkWithDB.DAO.JPA.Utils.*;

public class LotDAO implements DAO<Lot> {

    private final EntityManagerFactory entityManagerFactory;

    public LotDAO(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public Optional<Lot> get(Long identifier) {
        return Optional.of(entityManagerFactory.createEntityManager().find(Lot.class, identifier));
    }

    @Override
    public List<Lot> getAll(Lot identifier) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Lot> criteriaQuery = criteriaBuilder.createQuery(Lot.class);
        Root<Lot> lotRoot = criteriaQuery.from(Lot.class);

        Field[] fields = Arrays.stream(identifier.getClass().getDeclaredFields())
                .filter(field -> Objects.nonNull(getValue(field, identifier)))
                .filter(field -> !field.getType().equals(List.class))
                .toArray(Field[]::new);

        List<Predicate> searchCriteria = Arrays.stream(fields)
                .map(field -> convertToCriteriaPredicate(field, criteriaBuilder, lotRoot, identifier))
                .collect(Collectors.toList());

        criteriaQuery.select(lotRoot).where(criteriaBuilder.and(searchCriteria.toArray(new Predicate[0])));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Lot> getAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<Lot> query = entityManager.createQuery("select l from Lot l", Lot.class);

        return query.getResultList();
    }

    @Override
    public void save(Lot object) {
        conductInTransaction(entityManager -> entityManager.persist(object), entityManagerFactory.createEntityManager());
    }

    @Override
    public void delete(Lot object) {
        conductInTransaction(entityManager -> entityManager.remove(entityManager.merge(object)),
                entityManagerFactory.createEntityManager());
    }

    @Override
    public void update(Lot object) {
        conductInTransaction(entityManager -> entityManager.merge(object), entityManagerFactory.createEntityManager());
    }
}
