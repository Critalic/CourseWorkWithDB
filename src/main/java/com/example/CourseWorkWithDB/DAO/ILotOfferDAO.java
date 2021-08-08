package com.example.CourseWorkWithDB.DAO;

import com.example.CourseWorkWithDB.Model.LotOffer;

import java.sql.SQLException;
import java.util.List;

public interface ILotOfferDAO {
    void addLotOfferWithDescription(LotOffer offer) throws SQLException;

    void addLotOfferWithoutDescription(LotOffer offer) throws SQLException;

    List<LotOffer> getAllForLot(long id) throws SQLException;
}
