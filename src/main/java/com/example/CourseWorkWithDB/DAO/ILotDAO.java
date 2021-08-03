package com.example.CourseWorkWithDB.DAO;

import com.example.CourseWorkWithDB.Exceptions.DBError;
import com.example.CourseWorkWithDB.Exceptions.NoIDException;
import com.example.CourseWorkWithDB.Model.Lot;

import java.util.List;

public interface ILotDAO {
    Lot get(int id) throws NoIDException;
    String getLotOwner(String tenderId) throws NoIDException;

    List<Lot> getAll() throws DBError;
    List<Lot> getAllWithName(String name);
    List<Lot> getAllWithOwner(String owner);

    void createLot(Lot lot) throws DBError;
    void updateStatus(String tenderId, boolean value) throws NoIDException, DBError;

    void deleteLot(String id) throws DBError;
}
