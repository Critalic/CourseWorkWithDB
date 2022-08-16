package com.example.cw.controllers;

import com.example.cw.dao.jpa.DAO;
import com.example.cw.dao.jpa.implementations.JpaDaoFactory;
import com.example.cw.entity.Customer;
import com.example.cw.entity.Lot;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
@Disabled
public class LotDAOTest {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("cleverCloud");
    DAO<Lot> lotDAO = new JpaDaoFactory(factory).getDAO(Lot.class);
    DAO<Customer> customerDAO =new JpaDaoFactory(factory).getDAO(Customer.class);

    @Test
    @Disabled
    public void saveTest() {
        Customer customer = new Customer().setId(3L);
        Lot lot = new Lot("BMW", 32000., false, customer).setDescription("BMW e39 M5");
        lotDAO.save(lot);

//        System.out.println(customerDAO.get(3L).get().getLots().get(0).getName());
    }

    @Test
    @Disabled
    public void deleteTest() {
        Lot lot = lotDAO.get(2L).get();
        lotDAO.delete(lot);
    }

    @Test
    @Disabled
    public void getTest() {
        JpaDaoFactory lotdao = new JpaDaoFactory(factory);
        List<Lot> lots = lotdao.getDAO(Lot.class).getAll(new Lot().setCustomer(new Customer().setId(1L)));

        System.out.println(lots);
    }

    @Test
    @Disabled
    public void getAllTest() {
//        List<Lot> lots = lotDAO.getAll();

//        System.out.println(lots);
    }

    @Test
    @Disabled
    public void testUpdate() {
        Lot lot = new Lot().setId(6L).setName("Mercedes").setCustomer(new Customer().setId(16L)).setStartPrice(50.);
        lot.setStatus(true);

        lotDAO.update(lot);
    }
}
