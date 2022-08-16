package com.example.cw.dao.jpa;

import com.example.cw.entity.BasicEntity;

public interface DAOFactory {
    <T extends BasicEntity> DAO<T> getDAO(Class<T> type);
}
