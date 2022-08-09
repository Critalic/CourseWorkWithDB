package com.example.CourseWorkWithDB.Services;

import com.example.CourseWorkWithDB.DAO.JPA.DAO;
import com.example.CourseWorkWithDB.DAO.JPA.DAOFactory;
import com.example.CourseWorkWithDB.Entity.Customer;
import com.example.CourseWorkWithDB.Entity.Lot;
import com.example.CourseWorkWithDB.Entity.LotOffer;

import com.example.CourseWorkWithDB.Exceptions.DBException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.validation.constraints.DecimalMin;

public class LotService implements BusinessService {
    private final DAO<Lot> lotDAO;
    private final DAO<LotOffer> lotOfferDAO;

    public LotService(DAOFactory daoFactory) {
        this.lotDAO = daoFactory.getDAO(Lot.class);
        this.lotOfferDAO = daoFactory.getDAO(LotOffer.class);
    }

    public List<Lot> getLots() { //TODO pagination
        return lotDAO.getAll();
    }

    public List<Lot> getLotsWithOwner(long ownerID) {
        return lotDAO.getAll(new Lot().setCustomer(new Customer().setId(ownerID)));
    }

    public List<Lot> getLotsByName(String name) {
        return Stream.concat(lotDAO.getAll(new Lot().setName(name)).stream(),
                        lotDAO.getAll(new Lot().setDescription(name)).stream())
                .collect(Collectors.toList());
    }

    public Lot getLotById(long id) {
        return lotDAO.get(id).orElseThrow(() -> new DBException("Failed to get lot"));
    }

    public void changeStatus(long lotId) {
        Lot lot = lotDAO.get(lotId).orElseThrow(() -> new DBException("Failed to get lot"));
        lotDAO.update(lot.setStatus(!lot.getIsActive()));
    }

    public void deleteLot(long lotId) {
        lotDAO.delete(new Lot().setId(lotId));
    }

    public String generateURL() {
        return UUID.randomUUID().toString();
    }

    public void createNewLot(Long ownerID, String name, String description,
                             @DecimalMin(value = "0.01", message = "Price must be at least 0.01 ") Double startPrice) {
        lotDAO.save(new Lot(name, startPrice, false, new Customer().setId(ownerID))
                .setDescription(description));
    }
}
