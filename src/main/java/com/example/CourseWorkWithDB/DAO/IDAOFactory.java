package com.example.CourseWorkWithDB.DAO;

public interface IDAOFactory {
    ILotDAO getLotDAO ();
    ILotOfferDAO getLotOfferDAO();
    IUserDAO getUserDAO();
}
