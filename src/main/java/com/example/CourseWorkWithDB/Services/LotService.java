package com.example.CourseWorkWithDB.Services;

import com.example.CourseWorkWithDB.DAO.JPA.DAO;
import com.example.CourseWorkWithDB.DAO.JPA.DAOFactory;
import com.example.CourseWorkWithDB.Entity.Customer;
import com.example.CourseWorkWithDB.Entity.Lot;
import com.example.CourseWorkWithDB.Entity.LotOffer;
import com.example.CourseWorkWithDB.Exceptions.LessThanZeroException;
import com.example.CourseWorkWithDB.Validators.EmptyValidator;
import com.example.CourseWorkWithDB.Validators.NumberValidator;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LotService {
    private final DAOFactory daoFactory;
    private final DAO<Lot> lotDAO;
    private final DAO<LotOffer> lotOfferDAO;

    public LotService(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
        this.lotDAO = daoFactory.getDAO(Lot.class);
        this.lotOfferDAO = daoFactory.getDAO(LotOffer.class);
    }

    public List<Lot> getLots() { //TODO pagination
        return lotDAO.getAll();
    }

    public List<Lot> getLotsWithOwner(long ownerID) {
        return lotDAO.getAll(new Lot().setCustomer(new Customer().setId(ownerID)));
    }

    public List<LotOffer> getOffers(long lotId) {
        return lotOfferDAO.getAll(new LotOffer().setLot(new Lot().setId(lotId)));
    }

    public List<Lot> getLotsByName(String name) {
        name = EmptyValidator.checkIfEmpty(name, "Name");
        return Stream.concat(lotDAO.getAll(new Lot().setName(name)).stream(),
                        lotDAO.getAll(new Lot().setDescription(name)).stream())
                .collect(Collectors.toList());
    }

    public Optional<Lot> getLotById(long id) {
        return lotDAO.get(id);
    }

    public void setLotStatus(long lotId, boolean value) {
        lotDAO.update(lotDAO.get(lotId).orElseThrow(() -> new RuntimeException("Failed to get lot")).setActive(value));
    }

    public void deleteLot(long lotId) {
        lotDAO.delete(new Lot().setId(lotId));
    }

    public String generateURL() {
        return UUID.randomUUID().toString();
    }

    public void createNewLot(long ownerID, String name, String description, double startPrice)
            throws IllegalArgumentException, NullPointerException, LessThanZeroException {
        name = EmptyValidator.checkIfEmpty(name, "Name");
        description = EmptyValidator.checkIfEmpty(description, "Description");
        NumberValidator.moreThanZero(startPrice, "Start price");

        lotDAO.save(new Lot(name, startPrice, false, new Customer().setId(ownerID))
                .setDescription(description));
    }

}
