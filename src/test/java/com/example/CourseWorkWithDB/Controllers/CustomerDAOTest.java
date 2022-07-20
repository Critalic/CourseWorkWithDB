package com.example.CourseWorkWithDB.Controllers;

import com.example.CourseWorkWithDB.DAO.JPA.DAO;
import com.example.CourseWorkWithDB.DAO.JPA.Implementations.JpaDaoFactory;
import com.example.CourseWorkWithDB.Entity.Customer;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.jupiter.api.Test;


class CustomerDAOTest {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("cleverCloud");
    DAO<Customer> customerDAO = new JpaDaoFactory(factory).getDAO(Customer.class);

    @Test
    void testSave() {
        Customer customer = new Customer("admin4", "admin4@gmail.com", "123");

        customerDAO.save(customer);
    }

    @Test
    void testRemove() {
        Customer customer = customerDAO.get(9L).get();

        customerDAO.delete(customer);
    }

    @Test
    void testUpdate() {
        Customer customer = customerDAO.get(4L).get();
        customer.setPasswordHash("256");

        customerDAO.update(customer);
    }

    @Test
    void testGet() {
        List<Customer> customers =
        customerDAO.getAll(new Customer().setEmail("admin").setId(3L));

        customers.forEach(System.out::println);
    }

}
