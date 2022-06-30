package com.example.CourseWorkWithDB.Controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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

    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("cleverCloud");

    @Test
    @DisplayName("DBConnectionSelect")
    void testDBConnectionSelect() {
        EntityManager em = factory.createEntityManager();

        Query q = em.createQuery("select c from Customer c");
        List<Customer> customerEntities = q.getResultList();

        System.out.println(customerEntities);
    }

    @Test
    @DisplayName("DBConnectionInsert")
    void testDBConnectionInsert() {
        Customer customer = new Customer("admin2", "admin2@gmail.com", "123");
        customer.setLots(null);
        customer.setOffers(null);

        EntityManager em = factory.createEntityManager();

            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
            em.close();
        testCCPreserve(customer);

    }

    @Test
    void testCCPreserve(Customer customer) {
        EntityManager em = factory.createEntityManager();

        Query q = em.createQuery("select c from Customer c");
        List<Customer> customerEntities = q.getResultList();

        assertEquals(customerEntities.get(0), customer);
    }
}