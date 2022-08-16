package com.example.cw.services;

import com.example.cw.dao.jpa.DAO;
import com.example.cw.dao.jpa.DAOFactory;
import com.example.cw.entity.Customer;
import com.example.cw.entity.Lot;
import com.example.cw.exceptions.DBException;

import javax.validation.constraints.DecimalMin;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LotService implements BusinessService {
    private final DAO<Lot> lotDAO;

    public LotService(DAOFactory daoFactory) {
        this.lotDAO = daoFactory.getDAO(Lot.class);
    }

    public List<Lot> getActiveLots(int pageNumber, int sizeLimit) {
        return lotDAO.getAll(new Lot().setStatus(true), pageNumber, sizeLimit);
    }

    public Integer getSumOfRecords() {
        return lotDAO.getAll(new Lot().setStatus(true)).size();
    }

    public List<Lot> getLotsWithOwner(long ownerID) {
        return lotDAO.getAll(new Lot().setCustomer(new Customer().setId(ownerID)));
    }

    public List<Lot> getActiveLotsByName(String name) {
        return Stream.concat(lotDAO.getAll(new Lot().setName(name).setStatus(true)).stream(),
                        lotDAO.getAll(new Lot().setDescription(name).setStatus(true)).stream())
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
