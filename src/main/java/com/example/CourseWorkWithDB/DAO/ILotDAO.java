package com.example.CourseWorkWithDB.DAO;

import com.example.CourseWorkWithDB.Entity.Lot;

import java.sql.SQLException;
import java.util.List;

public interface ILotDAO {
    Lot getLot(long id) throws SQLException;

    List<Lot> getAll() throws SQLException;
    List<Lot> getAllThatContain(String name) throws SQLException;
    List<Lot> getAllWithOwner(long ownerId) throws SQLException;

    void addLot(Lot lot) throws SQLException;
    void updateStatus(long tenderId, boolean value) throws SQLException;

    void deleteLot(long id) throws SQLException;
}
