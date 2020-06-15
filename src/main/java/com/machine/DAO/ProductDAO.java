package com.machine.DAO;

import com.machine.DAO.AbstractDAO;
import com.machine.models.product.Product;

import java.sql.Connection;

public interface ProductDAO<E> extends AbstractDAO<E> {
    E getProductByName(String name);
    E getProductByCode(Integer code);


    void update(Connection connection, E product);

}
