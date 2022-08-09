package com.example.CourseWorkWithDB.Controllers;

import com.example.CourseWorkWithDB.DAO.JPA.DAO;
import com.example.CourseWorkWithDB.DAO.JPA.Implementations.JpaDaoFactory;
import com.example.CourseWorkWithDB.Entity.Customer;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@Disabled
public class CustomerDAOTest {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("cleverCloud");
    DAO<Customer> customerDAO = new JpaDaoFactory(factory).getDAO(Customer.class);

    @Test
    @Disabled
    public void testSave() {
        Customer customer = new Customer("admin5", "admin5@gmail.com", "123");

        customerDAO.save(customer);
    }

    @Test
    @Disabled
    public void testRemove() {
        Customer customer = customerDAO.getAll(new Customer().setEmail("vitalikkryvonosiuk@gmail.com")).get(0);

        customerDAO.delete(customer);
    }

    @Test
    @Disabled
    public void testUpdate() {
        Customer customer = customerDAO.get(4L).get();
        customer.setPasswordHash("246");

        customerDAO.update(customer);
    }

    @Test
    @Disabled
    public void testGet() {
        List<Customer> customers =
        customerDAO.getAll(new Customer().setEmail("admin").setId(3L));

        customers.forEach(System.out::println);
    }

}
