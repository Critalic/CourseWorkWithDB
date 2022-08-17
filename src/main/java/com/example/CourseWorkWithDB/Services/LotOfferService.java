package com.example.CourseWorkWithDB.Services;

import com.example.CourseWorkWithDB.DAO.IDAOFactory;
import com.example.CourseWorkWithDB.Exceptions.LessThanGivenException;
import com.example.CourseWorkWithDB.Exceptions.LessThanZeroException;
import com.example.CourseWorkWithDB.Model.Lot;
import com.example.CourseWorkWithDB.Model.LotOffer;
import com.example.CourseWorkWithDB.Validators.EmptyValidator;
import com.example.CourseWorkWithDB.Validators.NumberValidator;

import java.sql.SQLException;

public class LotOfferService {
    private final IDAOFactory daoFactory;

    public LotOfferService(IDAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public void createNewOfferWithoutDescription(int money, long lotId, long userID)
            throws LessThanZeroException, SQLException, LessThanGivenException {
        NumberValidator.moreThanZero(lotId, "Lot ID");
        Lot lot= daoFactory.getLotDAO().getLot(lotId);
        money = (int) NumberValidator.lessThanGivenNumber(money, lot.getStartPrice());
        daoFactory.getLotOfferDAO().addLotOfferWithoutDescription(new LotOffer(money, userID, lotId));
    }

    public void createNewOfferWithDescription(int money, long lotId, long userID, String description)
            throws LessThanZeroException, SQLException, LessThanGivenException, NullPointerException, IllegalArgumentException {
        description = EmptyValidator.checkIfEmpty(description, "Text");
        NumberValidator.moreThanZero(lotId, "Lot ID");
        Lot lot= daoFactory.getLotDAO().getLot(lotId);
        money = (int) NumberValidator.lessThanGivenNumber(money, lot.getStartPrice());
        daoFactory.getLotOfferDAO().addLotOfferWithoutDescription(new LotOffer(userID, lotId, description, money));
    }
}
