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
    public User getUser(String userLogin) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(UserQueries.getUserByLogin);
            statement.setString(1, userLogin);
            resultSet = statement.executeQuery();
            resultSet.next();
            return mapUser(resultSet);
        } finally {
            assert resultSet != null;
            resultSet.close();
            statement.close();
        }
    }

    @Override
    public void createUser(User user) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UserQueries.addUser);
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPasswordHash());
            statement.executeUpdate();
        } finally {
            assert statement != null;
            statement.close();
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
        static String getUserByLogin = "select * from customer where email = ?";
        static String addUser = "insert into customer (username, email, password) values" +
                " (?, ?, ?);";
    }
}
