package com.example.CourseWorkWithDB.DAO.JPArealizations;

import com.example.CourseWorkWithDB.DAO.IUserDAO;
import com.example.CourseWorkWithDB.Entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.sql.SQLException;

public class UserDAO implements IUserDAO {

    private EntityManagerFactory factory;

    public UserDAO(EntityManagerFactory factory) {
        this.factory = factory;
    }

    @Override
    public Customer getUser(String login) throws SQLException {
        return null;
    }

    @Override
    public void createUser(Customer customer) throws SQLException {

    }
}
