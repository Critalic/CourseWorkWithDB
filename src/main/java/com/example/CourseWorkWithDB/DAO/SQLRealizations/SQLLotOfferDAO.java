package com.example.CourseWorkWithDB.DAO.SQLRealizations;

import com.example.CourseWorkWithDB.DAO.ILotOfferDAO;
import com.example.CourseWorkWithDB.Exceptions.NoIDException;
import com.example.CourseWorkWithDB.Model.LotOffer;

import java.util.List;

public class SQLLotOfferDAO implements ILotOfferDAO {
    @Override
    public void createLotOffer(LotOffer offer) {

    }

    @Override
    public List<LotOffer> getAllFromLot(String id) throws NoIDException {
        return null;
    }
}
