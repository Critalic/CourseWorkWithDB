package com.example.CourseWorkWithDB.DAO.JPA.Implementations;

import com.example.CourseWorkWithDB.DAO.JPA.DAO;
import com.example.CourseWorkWithDB.DAO.JPA.DAOFactory;
import com.example.CourseWorkWithDB.Entity.BasicEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

import static com.example.CourseWorkWithDB.DAO.JPA.Utils.conductInTransaction;
import static com.example.CourseWorkWithDB.DAO.JPA.Utils.convertFieldsToPredicates;

public class JpaDaoFactory implements DAOFactory {

    private final EntityManagerFactory entityManagerFactory;

    public JpaDaoFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public <T extends BasicEntity> DAO<T> getDAO(Class<T> type) {
        return new DAO<T>() {
            @Override
            public Optional<T> get(Long identifier) {
                return Optional.of(entityManagerFactory.createEntityManager().find(type, identifier));
            }

            @Override
            public List<T> getAll(T identifier) {
                EntityManager manager = entityManagerFactory.createEntityManager();

                CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
                CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(type);
                Root<T> customerRoot = criteriaQuery.from(type);

                List<Predicate> searchCriteria = convertFieldsToPredicates(identifier, criteriaBuilder, customerRoot);

                criteriaQuery.select(customerRoot).where(criteriaBuilder.and(searchCriteria.toArray(new Predicate[0])));
                return manager.createQuery(criteriaQuery).getResultList();
            }

            @Override
            public List<T> getAll(T identifier, int pageNumber, int sizeLimit) { //TODO
                EntityManager manager = entityManagerFactory.createEntityManager();

                CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
                CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(type);
                Root<T> customerRoot = criteriaQuery.from(type);

                List<Predicate> searchCriteria = convertFieldsToPredicates(identifier, criteriaBuilder, customerRoot);

                criteriaQuery.select(customerRoot).where(criteriaBuilder.and(searchCriteria.toArray(new Predicate[0])));
                return manager.createQuery(criteriaQuery)
                        .setFirstResult(pageNumber - 1)
                        .setMaxResults(sizeLimit)
                        .getResultList();
            }

            @Override
            public List<T> getAll(int pageNumber, int sizeLimit) {
                EntityManager manager = entityManagerFactory.createEntityManager();

                CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
                CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(type);
                Root<T> customerRoot = criteriaQuery.from(type);

                return manager.createQuery(criteriaQuery.select(customerRoot))
                        .setFirstResult(pageNumber - 1)
                        .setMaxResults(sizeLimit)
                        .getResultList();
            }

            @Override
            public Long getSumOfRecords() {
                EntityManager manager = entityManagerFactory.createEntityManager();

                CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
                CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);

                criteriaQuery.select(criteriaBuilder.count(criteriaQuery.from(type)));

                return manager.createQuery(criteriaQuery).getSingleResult();
            }

            @Override
            public void save(T object) {
                conductInTransaction(entityManager -> entityManager.persist(object),
                        entityManagerFactory.createEntityManager());
            }

            @Override
            public void delete(T object) {
                conductInTransaction(entityManager -> entityManager.remove(entityManager.merge(object)),
                        entityManagerFactory.createEntityManager());
            }

            @Override
            public void update(T object) {
                conductInTransaction(entityManager -> entityManager.merge(object),
                        entityManagerFactory.createEntityManager());
            }
        };
    }
}
