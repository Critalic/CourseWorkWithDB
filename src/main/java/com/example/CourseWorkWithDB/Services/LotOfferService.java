package com.example.CourseWorkWithDB.Services;

import com.example.CourseWorkWithDB.DAO.JPA.DAO;
import com.example.CourseWorkWithDB.DAO.JPA.DAOFactory;
import com.example.CourseWorkWithDB.Entity.Customer;
import com.example.CourseWorkWithDB.Entity.Lot;
import com.example.CourseWorkWithDB.Entity.LotOffer;
import com.example.CourseWorkWithDB.Validators.EmptyValidator;
import com.example.CourseWorkWithDB.Validators.NumberValidator;

import java.sql.SQLException;

public class LotOfferService {
    private final DAO<LotOffer> lotOfferDAO;
    private final DAO<Lot> lotDAO;

    public LotOfferService(DAOFactory daoFactory) {
        this.lotOfferDAO = daoFactory.getDAO(LotOffer.class);
        this.lotDAO = daoFactory.getDAO(Lot.class);
    }

    public void createOffer(double money, long lotId, long userID, String description)
            throws LessThanZeroException, SQLException, LessThanGivenException, NullPointerException, IllegalArgumentException {
        description = EmptyValidator.checkIfEmpty(description, "Text");
        NumberValidator.moreThanZero(lotId, "Lot ID");
        Lot lot= lotDAO.get(lotId).orElseThrow(()-> new RuntimeException("Failed to find lot"));
        NumberValidator.lessThanGivenNumber(money, lot.getStartPrice());
        lotOfferDAO.save(new LotOffer(money, new Customer().setId(userID), lot).setDescription(description));
    }
}
