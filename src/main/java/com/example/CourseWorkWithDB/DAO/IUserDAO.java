package com.example.CourseWorkWithDB.DAO;

import com.example.CourseWorkWithDB.Entity.Customer;

import java.sql.SQLException;

public interface IUserDAO {
    Customer getUser(String login) throws SQLException;

    void createUser(Customer user) throws SQLException;
}
