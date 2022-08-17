package com.example.CourseWorkWithDB.DAO.SQLRealizations;

import com.example.CourseWorkWithDB.DAO.IDAOFactory;
import com.example.CourseWorkWithDB.DAO.ILotDAO;
import com.example.CourseWorkWithDB.DAO.ILotOfferDAO;
import com.example.CourseWorkWithDB.DAO.IUserDAO;

import java.sql.Connection;

public class SQLDAOFactory implements IDAOFactory {

    private final SQLUserDAO userDAO;
    private final SQLLotOfferDAO lotOfferDAO;
    private final SQLLotDAO lotDAO;

    public SQLDAOFactory(Connection connection) {
        userDAO = new SQLUserDAO(connection);
        lotOfferDAO = new SQLLotOfferDAO(connection);
        lotDAO = new SQLLotDAO(connection);
    }

    @Override
    public ILotOfferDAO getLotOfferDAO() {
        return lotOfferDAO;
    }

    @Override
    public ILotDAO getLotDAO() {
        return lotDAO;
    }

    @Override
    public IUserDAO getUserDAO() {
        return userDAO;
    }
}
