package com.example.CourseWorkWithDB.DAO.JPA;

import com.example.CourseWorkWithDB.Entity.BasicEntity;

public interface DAOFactory {
    <T extends BasicEntity> DAO<T> getDAO(Class<T> type);
}
