package com.example.CourseWorkWithDB.DAO;

import com.example.CourseWorkWithDB.Model.User;

import java.sql.SQLException;

public interface IUserDAO {
    User getUser(String login) throws SQLException;
    void createUser(User user) throws SQLException;
}
