package com.example.CourseWorkWithDB.DAO.SQLRealizations;

import com.example.CourseWorkWithDB.DAO.ILotDAO;
import com.example.CourseWorkWithDB.Exceptions.DBError;
import com.example.CourseWorkWithDB.Exceptions.NoIDException;
import com.example.CourseWorkWithDB.Model.Lot;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLLotDAO implements ILotDAO {
    private Connection connection;

    public SQLLotDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Lot getLot(long id) throws NoIDException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(LotQueries.getLotByID);
            statement.setLong(1, id);
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
    public List<Lot> getAll() throws DBError {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Lot> answer = new ArrayList<>();
        try {
            statement = connection.prepareStatement(LotQueries.getAllLots);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                answer.add(mapLot(resultSet));
            }
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
        return answer;
    }

    @Override
    public List<Lot> getAllThatContain(String name) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Lot> answer = new ArrayList<>();
        try {
            statement = connection.prepareStatement(LotQueries.getAllThatContain);
            statement.setString(1, ("%" + name + "%"));
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                answer.add(mapLot(resultSet));
            }
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
        return answer;
    }

    @Override
    public List<Lot> getAllWithOwner(long ownerId) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Lot> answer = new ArrayList<>();
        try {
            statement = connection.prepareStatement(LotQueries.getAllWithOwner);
            statement.setLong(1, ownerId);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                answer.add(mapLot(resultSet));
            }
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
        return answer;
    }

    @Override
    public void addLot(Lot lot) throws DBError {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(LotQueries.addLot);
            statement.setString(1, lot.getName());
            statement.setString(2, lot.getDescription());
            statement.setInt(3, lot.getStartPrice());
            statement.setLong(4, lot.getOwnerId());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public void updateStatus(long lotId, boolean value) throws NoIDException, DBError {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(LotQueries.updateStatus);
            statement.setBoolean(1, value);
            statement.setLong(2, lotId);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public void deleteLot(long lotId) throws DBError {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(LotQueries.deleteLot);
            statement.setLong(1, lotId);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
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
        static String getAllLots = "select * from lot";
        static String getAllWithOwner = "select lot.* from lottery.lot inner join lottery.customer on lottery.lot.customer_id = lottery.customer.id where lot.customer_id =?;";
        static String getAllThatContain = "select * from lottery.lot where name like ?;";
        static String addLot = "insert into lot (name, description, start_price, customer_id) values" +
                " (?, ?, ?, ?);";
        static String updateStatus = "update lot set is_active = ? where id = ?;";
        static String deleteLot = "delete from lot where id = ?";

    }
}


