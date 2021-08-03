package com.example.CourseWorkWithDB.DAO;

import com.example.CourseWorkWithDB.Exceptions.NoIDException;
import com.example.CourseWorkWithDB.Model.LotOffer;

import java.util.List;

public interface ILotOfferDAO {
    void createLotOffer(LotOffer offer);
    List<LotOffer> getAllFromLot(String id) throws NoIDException;
}
