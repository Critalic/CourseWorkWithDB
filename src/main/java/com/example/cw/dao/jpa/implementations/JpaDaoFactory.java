package com.example.cw.dao.jpa.implementations;

import com.example.cw.dao.DAOFactory;
import com.example.cw.dao.jpa.Utils;
import com.example.cw.dao.DAO;
import com.example.cw.model.BasicEntity;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class JpaDaoFactory implements DAOFactory {

    private final EntityManagerFactory entityManagerFactory;

    public JpaDaoFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public <T extends BasicEntity> DAO<T> getDAO(Class<T> type) {
        return new DAO<>() {
            @Override
            public Optional<T> get(Long identifier) {
                return Optional.of(entityManagerFactory.createEntityManager().find(type, identifier));
            }

            @Override
            public List<T> getAll(T identifier) {
                return entityManagerFactory.createEntityManager()
                    .createQuery(createQueryFromObject(identifier)).getResultList();
            }

            @Override
            public List<T> getAll(T identifier, int pageNumber, int sizeLimit) {
                return entityManagerFactory.createEntityManager()
                    .createQuery(createQueryFromObject(identifier))
                    .setFirstResult(pageNumber - 1)
                    .setMaxResults(sizeLimit)
                    .getResultList();
            }

            @Override
            public void save(T object) {
                Utils.conductInTransaction(entityManager -> entityManager.persist(object),
                    entityManagerFactory.createEntityManager());
            }

            @Override
            public void delete(T object) {
                Utils.conductInTransaction(entityManager -> entityManager.remove(entityManager.merge(object)),
                    entityManagerFactory.createEntityManager());
            }

            @Override
            public void update(T object) {
                Utils.conductInTransaction(entityManager -> entityManager.merge(object),
                    entityManagerFactory.createEntityManager());
            }

            private CriteriaQuery<T> createQueryFromObject(T object) {

                CriteriaBuilder criteriaBuilder = entityManagerFactory.getCriteriaBuilder();
                CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(type);
                Root<T> customerRoot = criteriaQuery.from(type);

                List<Predicate> searchCriteria = Utils.convertFieldsToPredicates(object, criteriaBuilder, customerRoot);

                return criteriaQuery.select(customerRoot)
                    .where(criteriaBuilder.and(searchCriteria.toArray(new Predicate[0])));
            }
        };
    }
}
