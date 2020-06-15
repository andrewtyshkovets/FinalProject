package com.machine.DAO.DAOImplementation;

import com.machine.DAO.ConnectionPool;
import com.machine.DAO.ProductDAO;
import com.machine.models.product.Product;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImplementation implements ProductDAO<Product> {

    private static ProductDAOImplementation instance;
    private static Logger logger = Logger.getLogger(ProductDAOImplementation.class);

    private ProductDAOImplementation() {
    }

    public static ProductDAO<Product> getInstance() {
        if (instance == null) {
            instance = new ProductDAOImplementation();
        }
        return instance;
    }

    @Override
    public Product getProductByName(String name) {
        if (name != null) {
            try (Connection connection = ConnectionPool.getConnection();
                 PreparedStatement statement = connection.prepareStatement("SELECT * FROM product WHERE lower(product_name) = ?")) {
                statement.setString(1, name.toLowerCase());
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    Product product = getProductFromResultSet(resultSet);
                    return product;
                }
            } catch (SQLException e) {
                logger.error(e);
            }
        }
        return null;
    }


    @Override
    public Product getProductByCode(Integer code) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("SELECT * FROM product WHERE product_code = ?")) {
            statement.setInt(1, code);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Product product = getProductFromResultSet(resultSet);
                return product;
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("SELECT * FROM product" )) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = getProductFromResultSet(resultSet);
                products.add(product);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return products;
    }

    @Override
    public void update(Product entity) {
        update(null, entity);
    }

    @Override
    public void update(Connection connection, Product product) {
        if (product != null) {
            Connection conn = (connection == null ? ConnectionPool.getConnection() : connection);
            try(PreparedStatement statement = conn.prepareStatement("UPDATE product SET product_code=?, product_name=?, quantity=?, price_per_unit=?, measure=? WHERE product_id=?")) {
                statement.setInt(1, product.getProductCode());
                statement.setString(2, product.getProductName());
                statement.setDouble(3, product.getQuantity());
                statement.setDouble(4, product.getPricePerMeasureOrQuantity());
                statement.setDouble(5, product.getMeasure());
                statement.setLong(6, product.getProductId());
                statement.executeUpdate();
            } catch (SQLException e) {
                logger.error(e);
            } finally {
                if (conn != null && connection == null) {
                    try {
                        conn.close();
                    } catch (SQLException e) { logger.error(e);}
                }
            }
        }
    }


    @Override
    public boolean delete(Long key) {
        if (key > 0L) {
            try(Connection connection = ConnectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement("DELETE FROM product WHERE product_id = ?")) {
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
    public Long create(Product entity) {
        if (entity != null) {
            try (Connection connection = ConnectionPool.getConnection();
                 PreparedStatement statement = connection.prepareStatement("INSERT INTO product "
                         + "(product_code, product_name, quantity, measure, price_per_unit) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
                statement.setInt(1, entity.getProductCode());
                statement.setString(2, entity.getProductName());
                statement.setDouble(3, entity.getQuantity());
                statement.setDouble(4, entity.getMeasure());
                statement.setDouble(5, entity.getPricePerMeasureOrQuantity());
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
    public Product getById(Long key) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("SELECT * FROM product WHERE product_id = ?")) {
            statement.setLong(1, key);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Product product = getProductFromResultSet(resultSet);
                return product;
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return null;
    }

    private Product getProductFromResultSet(ResultSet resultSet) {
        Product product = new Product();
        try {
            product.setProductId(resultSet.getLong("product_id"));
            product.setProductCode(resultSet.getInt("product_code"));
            product.setProductName(resultSet.getString("product_name"));
            product.setQuantity(resultSet.getInt("quantity"));
            product.setPricePerMeasureOrQuantity(resultSet.getDouble("price_per_unit"));
            product.setMeasure(resultSet.getDouble("measure"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }
}
