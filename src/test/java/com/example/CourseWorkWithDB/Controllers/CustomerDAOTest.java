package com.example.CourseWorkWithDB.Controllers;

import com.example.CourseWorkWithDB.DAO.JPA.Implementations.CustomerDAO;
import com.example.CourseWorkWithDB.DAO.JPA.DAO;
import com.example.CourseWorkWithDB.Entity.Customer;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;


public class CustomerDAOTest {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("cleverCloud");
    DAO<Customer> customerDAO = new CustomerDAO(factory);

    @Test
    public void testSave() {
        Customer customer = new Customer("admin4", "admin4@gmail.com", "123");

        customerDAO.save(customer);
    }

    @Test
    public void testRemove() {
        Customer customer = customerDAO.get(9L).get();

        customerDAO.delete(customer);
    }

    @Test
    public void testUpdate() {
        Customer customer = customerDAO.get(4L).get();
        customer.setPasswordHash("256");

        customerDAO.update(customer);
    }

    @Test
    public void testGet() {
        List<Customer> customers =
        customerDAO.getAll(new Customer().setEmail("admin").setId(3L));

        customers.forEach(System.out::println);
    }

}
