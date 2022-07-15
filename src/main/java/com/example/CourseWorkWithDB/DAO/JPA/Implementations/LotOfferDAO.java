package com.example.CourseWorkWithDB.DAO.JPA.Implementations;

import com.example.CourseWorkWithDB.DAO.JPA.DAO;
import com.example.CourseWorkWithDB.Entity.LotOffer;

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

public class LotOfferDAO implements DAO<LotOffer> {

    private final EntityManagerFactory entityManagerFactory;

    public LotOfferDAO(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public Optional<LotOffer> get(Long identifier) {
        return Optional.of(entityManagerFactory.createEntityManager().find(LotOffer.class, identifier));
    }

    @Override
    public List<LotOffer> getAll(LotOffer identifier) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<LotOffer> query = criteriaBuilder.createQuery(LotOffer.class);
        Root<LotOffer> root = query.from(LotOffer.class);

        List<Predicate> searchCriteria = convertFieldsToPredicates(identifier, criteriaBuilder, root);

        query.select(root).where(criteriaBuilder.and(searchCriteria.toArray(new Predicate[0])));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<LotOffer> getAll() {
        EntityManager manager = entityManagerFactory.createEntityManager();
        TypedQuery<LotOffer> query = manager.createQuery("select o from LotOffer o", LotOffer.class);
        return query.getResultList();
    }

    @Override
    public void save(LotOffer object) {
        conductInTransaction(manager -> manager.persist(object), entityManagerFactory.createEntityManager());
    }

    @Override
    public void delete(LotOffer object) {
        conductInTransaction(manager -> manager.remove(manager.merge(object)),
                entityManagerFactory.createEntityManager());
    }

    @Override
    public void update(LotOffer object) {
        conductInTransaction(manager -> manager.merge(object), entityManagerFactory.createEntityManager());
    }
}
