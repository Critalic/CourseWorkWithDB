package com.example.CourseWorkWithDB.Controllers;

import com.example.CourseWorkWithDB.DAO.JPA.DAO;
import com.example.CourseWorkWithDB.DAO.JPA.DAOFactory;
import com.example.CourseWorkWithDB.DAO.JPA.Implementations.JpaDaoFactory;
import com.example.CourseWorkWithDB.Entity.Customer;
import com.example.CourseWorkWithDB.Entity.Lot;
import com.example.CourseWorkWithDB.Entity.LotOffer;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.jupiter.api.Test;

class LotOfferDAOTest {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("cleverCloud");
    DAOFactory daoFactory = new JpaDaoFactory(factory);
    DAO<LotOffer> lotOfferDAO = daoFactory.getDAO(LotOffer.class);
    DAO<Lot> lotDAO = daoFactory.getDAO(Lot.class);
    DAO<Customer> customerDAO = daoFactory.getDAO(Customer.class);

    @Test
    void saveTest() {
        Customer customer = customerDAO.get(10L).get();
        Lot lot = lotDAO.get(4L).get();
        LotOffer lotOffer = new LotOffer(34., customer, lot);
        lotOfferDAO.save(lotOffer);

//        System.out.println(customer.getOffers().size());
//        System.out.println(lot.getOffers().size());
    }

    @Test
    void deleteTest() {
        LotOffer lotOffer = lotOfferDAO.get(3L).get();
        lotOfferDAO.delete(lotOffer);
    }

    @Test
    void getTest() {
        List<LotOffer> lots = lotOfferDAO.getAll(new LotOffer().setSuggestedPrice(34.)
                .setLot(new Lot().setId(4L)).setOfferer(new Customer().setId(10L)));
        System.out.println(lots);
    }

    @Test
    void testUpdate() {
        LotOffer lot = lotOfferDAO.get(2L).get();
        lot.setDescription("I want to buy your tea cups");

        lotOfferDAO.update(lot);

        System.out.println(lotOfferDAO.get(2L));
    }
}
