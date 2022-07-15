package com.example.CourseWorkWithDB.Services;

import com.example.CourseWorkWithDB.DAO.IDAOFactory;
import com.example.CourseWorkWithDB.DAO.JPA.DAO;
import com.example.CourseWorkWithDB.Entity.Lot;
import com.example.CourseWorkWithDB.Entity.LotOffer;
import com.example.CourseWorkWithDB.Exceptions.LessThanGivenException;
import com.example.CourseWorkWithDB.Exceptions.LessThanZeroException;
import com.example.CourseWorkWithDB.Validators.EmptyValidator;
import com.example.CourseWorkWithDB.Validators.NumberValidator;

import java.sql.SQLException;

public class LotOfferService {
//    private final DAO<LotOffer> lotOfferDAO;
//    private final DAO<Lot> lotDAO;
//
//    public LotOfferService(DAO<LotOffer> lotOfferDAO, DAO<Lot> lotDAO) {
//        this.lotOfferDAO = lotOfferDAO;
//        this.lotDAO = lotDAO;
//    }
//
//    public void createNewOfferWithoutDescription(double money, long lotId, long userID)
//            throws LessThanZeroException, SQLException, LessThanGivenException {
//        NumberValidator.moreThanZero(lotId, "Lot ID");
//        Lot lot= lotDAO.get(lotId).orElseThrow(()-> new RuntimeException("No lot with given ID found"));
//        NumberValidator.lessThanGivenNumber(money, lot.getStartPrice());
//        daoFactory.getLotOfferDAO().addLotOfferWithoutDescription(new LotOffer(money, userID, lotId));
//    }
//
//    public void createNewOfferWithDescription(int money, long lotId, long userID, String description)
//            throws LessThanZeroException, SQLException, LessThanGivenException, NullPointerException, IllegalArgumentException {
//        description = EmptyValidator.checkIfEmpty(description, "Text");
//        NumberValidator.moreThanZero(lotId, "Lot ID");
//        Lot lot= daoFactory.getLotDAO().getLot(lotId);
//        money = (int) NumberValidator.lessThanGivenNumber(money, lot.getStartPrice());
//        daoFactory.getLotOfferDAO().addLotOfferWithoutDescription(new LotOffer(userID, lotId, description, money));
//    }
}
