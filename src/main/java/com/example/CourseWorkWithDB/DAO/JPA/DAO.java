package com.example.CourseWorkWithDB.DAO.JPA;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {

    Optional<T> get(Long identifier);

    List<T> getAll(T identifier);

    List<T> getAll();

    void save(T object);

    void delete(T identifier);

    void update(T identifier);
}
