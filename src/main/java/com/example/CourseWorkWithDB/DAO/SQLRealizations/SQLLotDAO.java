package com.example.CourseWorkWithDB.DAO.SQLRealizations;

import com.example.CourseWorkWithDB.DAO.ILotDAO;
import com.example.CourseWorkWithDB.Exceptions.DBError;
import com.example.CourseWorkWithDB.Exceptions.NoIDException;
import com.example.CourseWorkWithDB.Model.Lot;

import java.sql.*;
import java.util.List;

public class SQLLotDAO implements ILotDAO {
    private Connection connection;

    public SQLLotDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Lot get(int id) throws NoIDException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(LotQueries.getLotByID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            resultSet.next();
            return mapLot(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        } finally {
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
    }

    @Override
    public String getLotOwner(String lotId) throws NoIDException {
//        PreparedStatement statement = null;
//        ResultSet resultSet = null;
//        try {
//            statement = connection.prepareStatement(LotQueries.getLotByID);
//            resultSet = statement.executeQuery();
//            while(resultSet.next()) {
//                mapLot(resultSet);
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
        return null;
    }

    @Override
    public List<Lot> getAll() throws DBError {
        return null;
    }

    @Override
    public List<Lot> getAllWithName(String name) {
        return null;
    }

    @Override
    public List<Lot> getAllWithOwner(String owner) {
        return null;
    }

    @Override
    public void createLot(Lot lot) throws DBError {

    }

    @Override
    public void updateStatus(String tenderId, boolean value) throws NoIDException, DBError {

    }

    @Override
    public void deleteLot(String id) throws DBError {

    }

    private Lot mapLot (ResultSet set) throws SQLException {
        Lot lot = new Lot(
                set.getLong("id"),
                set.getLong("customer_id"),
                set.getString("description"),
                set.getString("name"),
                set.getInt("start_price"),
                set.getBoolean("is_active"));
        return lot;
    }

    private static class LotQueries {
        static String getLotByID = "select * from lot where id = ?";
    }
}


