package com.example.CourseWorkWithDB.DAO;


import com.example.CourseWorkWithDB.Exceptions.AlreadyExistsError;
import com.example.CourseWorkWithDB.Exceptions.DBError;
import com.example.CourseWorkWithDB.Exceptions.InvalidEmailException;
import com.example.CourseWorkWithDB.Exceptions.NoIDException;
import com.example.CourseWorkWithDB.Model.User;

public interface IUserDAO {
    User getUser(String login) throws NoIDException;
    void createUser(User user) throws DBError, AlreadyExistsError, InvalidEmailException;
}
