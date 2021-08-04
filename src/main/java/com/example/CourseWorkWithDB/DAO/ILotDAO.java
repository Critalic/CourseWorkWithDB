package com.example.CourseWorkWithDB.DAO;

import com.example.CourseWorkWithDB.Exceptions.DBError;
import com.example.CourseWorkWithDB.Exceptions.NoIDException;
import com.example.CourseWorkWithDB.Model.Lot;

import java.util.List;

public interface ILotDAO {
    Lot getLot(long id) throws NoIDException;

    List<Lot> getAll() throws DBError;
    List<Lot> getAllThatContain(String name);
    List<Lot> getAllWithOwner(long ownerId);

    void addLot(Lot lot) throws DBError;
    void updateStatus(long tenderId, boolean value) throws NoIDException, DBError;

    void deleteLot(long id) throws DBError;
}
