package com.example.CourseWorkWithDB.Controllers;

import com.example.CourseWorkWithDB.DAO.JPA.Implementations.CustomerDAO;
import com.example.CourseWorkWithDB.DAO.JPA.DAO;
import com.example.CourseWorkWithDB.DAO.JPA.Implementations.LotDAO;
import com.example.CourseWorkWithDB.DAO.JPA.Implementations.LotOfferDAO;
import com.example.CourseWorkWithDB.Entity.Customer;
import com.example.CourseWorkWithDB.Entity.Lot;
import com.example.CourseWorkWithDB.Entity.LotOffer;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@Disabled
public class LotOfferDAOTest {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("cleverCloud");
    DAO<LotOffer> lotOfferDAO = new LotOfferDAO(factory);
    DAO<Lot> lotDAO = new LotDAO(factory);
    DAO<Customer> customerDAO = new CustomerDAO(factory);

    @Test
    @Disabled
    public void saveTest() {
        Customer customer = customerDAO.get(10L).get();
        Lot lot = lotDAO.get(4L).get();
        LotOffer lotOffer = new LotOffer(34., customer, lot);
        lotOfferDAO.save(lotOffer);

        System.out.println(customer.getOffers().size());
        System.out.println(lot.getOffers().size());
    }

    @Test
    @Disabled
    public void deleteTest() {
        LotOffer lotOffer = lotOfferDAO.get(3L).get();
        lotOfferDAO.delete(lotOffer);
    }

    @Test
    @Disabled
    public void getTest() {
        List<LotOffer> lots = lotOfferDAO.getAll(new LotOffer().setSuggestedPrice(34.)
                .setLot(new Lot().setId(4L)).setOfferer(new Customer().setId(10L)));
        System.out.println(lots);
    }

    @Test
    @Disabled
    public void testUpdate() {
        LotOffer lot = lotOfferDAO.get(2L).get();
        lot.setDescription("I want to buy your tea cups");

        lotOfferDAO.update(lot);

        System.out.println(lotOfferDAO.get(2L));
    }
}
