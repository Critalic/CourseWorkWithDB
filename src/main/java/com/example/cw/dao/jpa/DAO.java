package com.example.cw.dao.jpa;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {

    Optional<T> get(Long identifier);

    List<T> getAll(T identifier);

    List<T> getAll(T identifier, int pageNumber, int sizeLimit);

    void save(T object);

    void delete(T object);

    void update(T object);
}
