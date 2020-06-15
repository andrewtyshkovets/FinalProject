package com.machine.DAO;

import com.machine.exceptions.NotEnoughProductException;

import java.sql.Connection;

public interface BillDAO<E> extends AbstractDAO<E> {
    boolean addProductToBill(Long BillId, Long productId,double quantity,double measure,double price) throws NotEnoughProductException;
    boolean ifCancelled(Long key);

    Long create(Connection connection, E entity);
}
