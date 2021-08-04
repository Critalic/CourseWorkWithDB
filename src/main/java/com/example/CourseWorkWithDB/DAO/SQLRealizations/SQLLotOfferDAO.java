package com.example.CourseWorkWithDB.DAO.SQLRealizations;

import com.example.CourseWorkWithDB.DAO.ILotOfferDAO;
import com.example.CourseWorkWithDB.Exceptions.NoIDException;
import com.example.CourseWorkWithDB.Model.LotOffer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLLotOfferDAO implements ILotOfferDAO {
    private final Connection connection;

    public SQLLotOfferDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addLotOfferWithDescription(LotOffer offer) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(LotOfferQueries.addOfferWithDescription);
            statement.setString(1, offer.getDescription());
            statement.setInt(2, offer.getMoney());
            statement.setLong(3, offer.getOwner_id());
            statement.setLong(4, offer.getLot_id());
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
    public void addLotOfferWithoutDescription(LotOffer offer) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(LotOfferQueries.addOfferWithoutDescription);
            statement.setInt(1, offer.getMoney());
            statement.setLong(2, offer.getOwner_id());
            statement.setLong(3, offer.getLot_id());
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
    public List<LotOffer> getAllForLot(long lotId) throws NoIDException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<LotOffer> answer = new ArrayList<>();
        try {
            statement = connection.prepareStatement(LotOfferQueries.getAllForLot);
            statement.setLong(1, lotId);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                answer.add(mapLotOffer(resultSet));
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

    private LotOffer mapLotOffer(ResultSet set) throws SQLException {
        return new LotOffer(
                set.getLong("customer_id"),
                set.getLong("lot_id"),
                set.getString("description"),
                set.getInt("suggested_price"));
    }

    private static class LotOfferQueries {
        static String getAllForLot = "select * from lot_offer where lot_id = ?";
        static String addOfferWithDescription = "insert into lot_offer (description, suggested_price, customer_id, lot_id) values" +
                " (?, ?, ?, ?);";
        static String addOfferWithoutDescription = "insert into lot_offer (suggested_price, customer_id, lot_id) values" +
                " (?, ?, ?);";
    }
}
