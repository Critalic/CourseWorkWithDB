package com.example.CourseWorkWithDB.DAO;

import com.example.CourseWorkWithDB.Exceptions.NoIDException;
import com.example.CourseWorkWithDB.Model.LotOffer;

import java.util.List;

public interface ILotOfferDAO {
    void addLotOfferWithDescription(LotOffer offer);

    void addLotOfferWithoutDescription(LotOffer offer);

    List<LotOffer> getAllForLot(long id) throws NoIDException;
}
