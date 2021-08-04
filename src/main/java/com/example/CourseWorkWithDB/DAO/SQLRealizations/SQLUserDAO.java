package com.example.CourseWorkWithDB.DAO.SQLRealizations;

import com.example.CourseWorkWithDB.DAO.IUserDAO;
import com.example.CourseWorkWithDB.Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLUserDAO implements IUserDAO {
    private final Connection connection;

    public SQLUserDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User getUser(long userId) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(UserQueries.getUserById);
            statement.setLong(1, userId);
            resultSet = statement.executeQuery();
            resultSet.next();
            return mapUser(resultSet);
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
    public void createUser(User user) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UserQueries.addUser);
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPasswordHash());
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

    private User mapUser(ResultSet set) throws SQLException {
        return new User(
                set.getLong("id"),
                set.getString("username"),
                set.getString("email"),
                set.getString("password"));
    }

    private static class UserQueries {
        static String getUserById = "select * from customer where id = ?";
        static String addUser = "insert into customer (username, email, password) values" +
                " (?, ?, ?);";
    }
}
