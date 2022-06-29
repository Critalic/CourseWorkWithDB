package com.example.CourseWorkWithDB.Controllers;

import com.example.CourseWorkWithDB.Model.JPA.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import java.util.List;
import java.util.Optional;

class FrontControllerTest {

    @Test
    @DisplayName("DBConnectionSelect")
    public void testDBConnectionSelect() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");

        EntityManager em = factory.createEntityManager();

        Query q = em.createQuery("select c from Customer c");
        List<Customer> customerEntities = q.getResultList();

        System.out.println(customerEntities);
    }

    @Test
    @DisplayName("DBConnectionInsert")
    public void testDBConnectionInsert() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("cleverCloud");

        EntityManager em = factory.createEntityManager();

        Query q = em.createQuery("select c from Customer c");
        Optional<List<Customer>> customerEntities = Optional.of(q.getResultList());

        System.out.println(customerEntities.get());
    }
}