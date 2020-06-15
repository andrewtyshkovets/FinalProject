package com.machine.DAO;

import java.util.List;

/**
 * Interface for DAO to Database
 * @param <E> entity of model
 */
public interface AbstractDAO<E> {
    List<E> getAll();

    void update(E entity);

    boolean delete(Long key);

    Long create(E entity);

    E getById(Long key);

}
