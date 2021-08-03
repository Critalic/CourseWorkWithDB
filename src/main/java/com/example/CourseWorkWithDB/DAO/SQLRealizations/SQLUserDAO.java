package com.example.CourseWorkWithDB.DAO.SQLRealizations;

import com.example.CourseWorkWithDB.DAO.IUserDAO;
import com.example.CourseWorkWithDB.Exceptions.AlreadyExistsError;
import com.example.CourseWorkWithDB.Exceptions.DBError;
import com.example.CourseWorkWithDB.Exceptions.InvalidEmailException;
import com.example.CourseWorkWithDB.Exceptions.NoIDException;
import com.example.CourseWorkWithDB.Model.User;

public class SQLUserDAO implements IUserDAO {
    @Override
    public User getUser(String login) throws NoIDException {
        return null;
    }

    @Override
    public void createUser(User user) throws DBError, AlreadyExistsError, InvalidEmailException {

    }
}
