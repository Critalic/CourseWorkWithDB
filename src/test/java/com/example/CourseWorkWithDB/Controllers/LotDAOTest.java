package com.example.CourseWorkWithDB.Controllers;

import com.example.CourseWorkWithDB.DAO.JPA.DAO;
import com.example.CourseWorkWithDB.DAO.JPA.Implementations.JpaDaoFactory;
import com.example.CourseWorkWithDB.Entity.Customer;
import com.example.CourseWorkWithDB.Entity.Lot;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.jupiter.api.Test;

class LotDAOTest {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("cleverCloud");
    DAO<Lot> lotDAO = new JpaDaoFactory(factory).getDAO(Lot.class);
    DAO<Customer> customerDAO =new JpaDaoFactory(factory).getDAO(Customer.class);

    @Test
    void saveTest() {
        Customer customer = new Customer().setId(3L);
        Lot lot = new Lot("BMW", 32000., false, customer).setDescription("BMW e39 M5");
        lotDAO.save(lot);

//        System.out.println(customerDAO.get(3L).get().getLots().get(0).getName());
    }

    @Test
    void deleteTest() {
        Lot lot = lotDAO.get(2L).get();
        lotDAO.delete(lot);
    }

    @Test
    void getTest() {
        JpaDaoFactory lotdao = new JpaDaoFactory(factory);
        List<Lot> lots = lotdao.getDAO(Lot.class).getAll(new Lot().setCustomer(new Customer().setId(1L)));

        System.out.println(lots);
    }

    @Test
    void getAllTest() {
        List<Lot> lots = lotDAO.getAll();

        System.out.println(lots);
    }

    @Test
    void testUpdate() {
        Lot lot = lotDAO.get(4L).get();
        lot.setActive(true);

        lotDAO.update(lot);
    }
}
