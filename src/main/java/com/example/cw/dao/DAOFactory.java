package com.example.cw.dao;

import com.example.cw.model.BasicEntity;

public interface DAOFactory {
    <T extends BasicEntity> DAO<T> getDAO(Class<T> type);
}
