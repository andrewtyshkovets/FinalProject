package com.machine.DAO;

import com.machine.DAO.AbstractDAO;
import com.machine.models.user.User;

import java.util.List;

public interface UserDAO<E> extends AbstractDAO<E> {
    User findByLoginAndPassword(String login, String password);

    boolean checkIfExist(String login);

    List<User> getAll(String where);

    Integer getRoleId(String role);
}
