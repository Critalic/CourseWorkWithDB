package com.example.CourseWorkWithDB.Services;

import com.example.CourseWorkWithDB.DAO.IDAOFactory;
import com.example.CourseWorkWithDB.Exceptions.LessThanZeroException;
import com.example.CourseWorkWithDB.Model.Lot;
import com.example.CourseWorkWithDB.Model.LotOffer;
import com.example.CourseWorkWithDB.Model.User;
import com.example.CourseWorkWithDB.Validators.EmptyValidator;
import com.example.CourseWorkWithDB.Validators.NumberValidator;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class LotService {
    private final IDAOFactory daoFactory;

    public LotService(IDAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public List<Lot> getLots() throws SQLException {
        return daoFactory.getLotDAO().getAll();
    }

    public List<Lot> getLotsWithOwner(long ownerID) throws SQLException {
        return daoFactory.getLotDAO().getAllWithOwner(ownerID);
    }

    public List<LotOffer> getOffers (long lotId) throws SQLException {
        return daoFactory.getLotOfferDAO().getAllForLot(lotId);
    }

    public List<Lot> getLotsByName(String name) throws SQLException {
        name = EmptyValidator.checkIfEmpty(name, "Name");
        return daoFactory.getLotDAO().getAllThatContain(name);
    }

    public Lot getLotById(long id) throws SQLException {
        return daoFactory.getLotDAO().getLot(id);
    }

    public void setLotStatus(long lotId, boolean value)
            throws SQLException {
        daoFactory.getLotDAO().updateStatus(lotId, value);
    }

    public void deleteLot(long lotId, User owner) throws SQLException {
            daoFactory.getLotDAO().deleteLot(lotId);
    }

    public String generateURL () {
        return  UUID.randomUUID().toString();
    }

    public void createNewLot(long ownerID, String name, String description, int startPrice)
            throws LessThanZeroException, SQLException, IllegalArgumentException, NullPointerException {
        name = EmptyValidator.checkIfEmpty(name, "Name");
        description = EmptyValidator.checkIfEmpty(description, "Description");
        startPrice = (int) NumberValidator.moreThanZero(startPrice, "Start price");
        daoFactory.getLotDAO().addLot(new Lot(name, description, startPrice, ownerID));
    }

}
