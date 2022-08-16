package com.example.cw.services;

import com.example.cw.dao.jpa.DAO;
import com.example.cw.dao.jpa.DAOFactory;
import com.example.cw.entity.Customer;
import com.example.cw.entity.Lot;
import com.example.cw.entity.LotOffer;
import com.example.cw.exceptions.DBException;

import java.util.List;

public class LotOfferService implements BusinessService {
    private final DAO<LotOffer> lotOfferDAO;
    private final DAO<Lot> lotDAO;

    public LotOfferService(DAOFactory daoFactory) {
        this.lotOfferDAO = daoFactory.getDAO(LotOffer.class);
        this.lotDAO = daoFactory.getDAO(Lot.class);
    }

    public void createOffer(Double money, Long lotId, Long userID, String description) {
        Lot lot= lotDAO.get(lotId).orElseThrow(()-> new DBException("Failed to find lot"));

        if(lot.getStartPrice()>= money) {
            throw new IllegalArgumentException("Bid must be bigger than the current price");
        }
        lotDAO.update(lot.setStartPrice(money));
        lotOfferDAO.save(new LotOffer(money, new Customer().setId(userID), lot).setDescription(description));
    }

    public List<LotOffer> getOffersForLot(long lotId) {
        return lotOfferDAO.getAll(new LotOffer().setLot(new Lot().setId(lotId)));
    }

    public Integer getSumOfRecords() {
        return 2;
    }
}
