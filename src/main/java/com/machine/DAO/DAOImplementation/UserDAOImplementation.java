package com.machine.DAO.DAOImplementation;

import com.machine.DAO.ConnectionPool;
import com.machine.DAO.UserDAO;
import com.machine.models.user.User;
import com.machine.models.user.role.Role;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImplementation implements UserDAO<User> {

    private static UserDAOImplementation instance;
    private static Logger logger = Logger.getLogger(UserDAO.class);

    private UserDAOImplementation() {
    }

    public static UserDAO<User> getInstance() {
        if (instance == null) {
            instance = new UserDAOImplementation();
        }
        return instance;
    }


    @Override
    public User findByLoginAndPassword(String login, String password) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("SELECT * FROM user INNER JOIN role ON user.user_role_id = role.role_id WHERE username = ? AND password = ?")) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("user_id"));
                user.setUsername(login);
                user.setPassword(password);
                user.setUserRoleId(resultSet.getInt("user_role_id"));
                user.setFullName(resultSet.getString("full_name"));
                Role role = Role.valueOf(resultSet.getString("role_name"));
                user.setRole(role);
                return user;
            }
        } catch (SQLException e) {
            logger.error("Error while searching for user", e);
        }
        return null;
    }

    @Override
    public boolean checkIfExist(String login){
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("SELECT * FROM user WHERE username = ?")) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            logger.error("Error while searching for user", e);
        }
        return false;
    }


    @Override
    public List<User> getAll() {
        return getAll(null);
    }

    @Override
    public List<User> getAll(String where) {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM user" + (where != null ? " WHERE " + where : ""));
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("user_id"));
                user.setUserRoleId(rs.getInt("user_role_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setFullName(rs.getString("full_name"));
                users.add(user);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return users;
    }


    @Override
    public void update(User user) {
        if (user != null) {
            try (Connection connection = ConnectionPool.getConnection();
                 PreparedStatement statement = connection.prepareStatement("UPDATE user SET username=?, password=?, full_name=?, user_role_id=? WHERE user_id=?")) {
                statement.setString(1, user.getUsername());
                statement.setString(2, user.getPassword());
                statement.setString(3, user.getFullName());
                statement.setInt(4, user.getUserRoleId());
                statement.setLong(5, user.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                logger.error(e);
            }
        }
    }

    @Override
    public boolean delete(Long key) {
        if (key > 0) {
            try (Connection connection = ConnectionPool.getConnection();
                 PreparedStatement statement = connection.prepareStatement("DELETE FROM user WHERE user_id = ?")) {
                statement.setLong(1, key);
                statement.executeUpdate();
                return true;
            } catch (SQLException e) {
                logger.error(e);
            }
        }
        return false;
    }

    @Override
    public Long create(User entity) {
        if (entity != null) {
            try (Connection connection = ConnectionPool.getConnection();
                 PreparedStatement statement = connection.prepareStatement("INSERT INTO user "
                         + "(user_role_id, username, password, full_name) VALUES (?, ?, ?,?)", Statement.RETURN_GENERATED_KEYS)) {
                statement.setInt(1, entity.getUserRoleId());
                statement.setString(2, entity.getUsername());
                statement.setString(3, entity.getPassword());
                statement.setString(4, entity.getFullName());
                statement.executeUpdate();
                ResultSet rs = statement.getGeneratedKeys();
                rs.next();
                return rs.getLong(1);
            } catch (SQLException e) {
                logger.error(e);
            }
        }
        return null;
    }

    @Override
    public User getById(Long key) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("SELECT * FROM user INNER JOIN role ON user.user_role_id = role.role_id WHERE user_id = ?")) {
            statement.setLong(1, key);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(key);
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setUserRoleId(resultSet.getInt("user_role_id"));
                user.setFullName(resultSet.getString("full_name"));
                Role role = Role.valueOf(resultSet.getString("role_name"));
                user.setRole(role);
                return user;
            }
        } catch (SQLException e) {
            logger.error("Error while searching for user", e);
        }
        return null;
    }
    @Override
    public Integer getRoleId(String role){
        Integer roleId = null;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("SELECT * FROM role WHERE role_name = ?")) {
            statement.setString(1, role);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                roleId = resultSet.getInt("role_id");
                return roleId;
            }
        } catch (SQLException e) {
            logger.error("Error while searching for role", e);
        }
        return roleId;
    }
}
