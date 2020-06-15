package com.machine.DAO.DAOImplementation;

import com.machine.DAO.ConnectionPool;
import com.machine.DAO.BillDAO;
import com.machine.DAO.DAOFactory;
import com.machine.DAO.ProductDAO;
import com.machine.exceptions.NotEnoughProductException;
import com.machine.models.bill.Bill;
import com.machine.models.DateTimeHelper;
import com.machine.models.product.Product;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillDAOImplementation implements BillDAO<Bill> {

    private static BillDAOImplementation instance;
    private static Logger logger = Logger.getLogger(BillDAOImplementation.class);

    private BillDAOImplementation() {
    }

    public static BillDAO<Bill> getInstance() {
        if (instance == null) {
            instance = new BillDAOImplementation();
        }
        return instance;
    }

    @Override
    public boolean ifCancelled(Long key) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("SELECT * FROM bill_head WHERE bill_head.bill_id = ?")) {
            statement.setLong(1, key);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                if (resultSet.getBoolean("is_cancelled") == false) {
                    return false;
                }
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return true;
    }

    @Override
    public List<Bill> getAll() {
        List<Bill> bills = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM bill_head");
            while (resultSet.next()) {
                Bill bill = new Bill();
                bill = getById(resultSet.getLong("bill_id"));
                bills.add(bill);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return bills;
    }


    @Override
    public void update(Bill entity) {
        if (entity != null) {
            try (Connection connection = ConnectionPool.getConnection();
                 PreparedStatement statement = connection.prepareStatement(
                         "UPDATE bill_head SET user_id = ?, cashbox_id = ?, date = ?, total_price = ?, is_cancelled = ? WHERE bill_id = ?")) {
                statement.setLong(1, entity.getCreatorUserId());
                statement.setLong(2, entity.getCashBoxId());
                statement.setString(3, DateTimeHelper.LocalDateTimeToString(entity.getCurrentDate()));
                statement.setDouble(4, entity.getTotalPrice());
                statement.setBoolean(5, entity.isCancelled());
                statement.setLong(6, entity.getBillId());
                statement.executeUpdate();
            } catch (SQLException e) {
                logger.error(e);
            }
        }
    }

    @Override
    public boolean delete(Long key) {
        if (key > 0) {
            try (Connection connection1 = ConnectionPool.getConnection();
                 PreparedStatement statement1 = connection1.prepareStatement("DELETE FROM bill_body WHERE bill_id = ?")) {
                statement1.setLong(1, key);
                statement1.executeUpdate();
            } catch (SQLException e) {
                logger.error(e);
            }
            try (Connection connection = ConnectionPool.getConnection();
                 PreparedStatement statement = connection.prepareStatement("DELETE FROM bill_head WHERE bill_id = ?")) {
                statement.setLong(1, key);
                statement.executeUpdate();
            } catch (SQLException e) {
                logger.error(e);
            }

            return true;
        }
        return false;
    }

    @Override
    public Long create(Bill entity) {
        return create(null, entity);
    }

    @Override
    public Long create(Connection connection, Bill entity) {
        if (entity != null) {
            Connection conn = (connection == null ? ConnectionPool.getConnection() : connection);
            try (PreparedStatement statement = conn.prepareStatement("INSERT INTO bill_head (user_id, cashbox_id, date, total_price, is_cancelled) "
                    + "VALUES (?, ?, ?, ?,?)", Statement.RETURN_GENERATED_KEYS)) {
                statement.setLong(1, entity.getCreatorUserId());
                statement.setInt(2, entity.getCashBoxId());
                statement.setString(3, DateTimeHelper.LocalDateTimeToString(entity.getCurrentDate()));
                statement.setDouble(4, entity.getTotalPrice());
                statement.setBoolean(5, entity.isCancelled());
                statement.executeUpdate();
                ResultSet rs = statement.getGeneratedKeys();
                rs.next();
                return rs.getLong(1);
            } catch (SQLException e) {
                logger.error(e);
            } finally {
                if (conn != null && connection == null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    @Override
    public Bill getById(Long key) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("SELECT * FROM bill_head LEFT JOIN bill_body ON bill_head.bill_id = bill_body.bill_id WHERE bill_head.bill_id = ?")) {
            statement.setLong(1, key);
            ResultSet resultSet = statement.executeQuery();
            Bill bill = new Bill();
            while (resultSet.next()) {
                bill.setBillId(resultSet.getLong("bill_id"));
                bill.setCreatorUserId(resultSet.getLong("user_id"));
                bill.setCashBoxId(resultSet.getInt("cashbox_id"));
                bill.setCurrentDate(DateTimeHelper.StringToLocalDateTime(resultSet.getString("date")));
                bill.setTotalPrice(resultSet.getDouble("total_price"));
                bill.setCancelled(resultSet.getBoolean("is_cancelled"));
                if (resultSet.getDouble("quantity") == 0) {
                    if (bill.getProductAmount().get(resultSet.getInt("product_id")) != null) {
                        bill.getProductAmount().put(resultSet.getInt("product_id"),
                                bill.getProductAmount().get(resultSet.getInt("product_id")) + resultSet.getDouble("measure"));
                    } else {
                        bill.getProductAmount().put(resultSet.getInt("product_id"), resultSet.getDouble("measure"));
                    }
                } else {
                    if (bill.getProductAmount().get(resultSet.getInt("product_id")) != null) {
                        bill.getProductAmount().put(resultSet.getInt("product_id"),
                                bill.getProductAmount().get(resultSet.getInt("product_id")) + resultSet.getDouble("quantity"));
                    } else {
                        bill.getProductAmount().put(resultSet.getInt("product_id"), resultSet.getDouble("quantity"));
                    }
                }
                if (bill.getProductPrice().get(resultSet.getInt("product_id")) != null) {
                    bill.getProductPrice().put(resultSet.getInt("product_id"),
                            bill.getProductPrice().get(resultSet.getInt("product_id")) + resultSet.getDouble("price"));
                } else {
                    bill.getProductPrice().put(resultSet.getInt("product_id"), resultSet.getDouble("price"));
                }
            }
            return bill;
        } catch (SQLException e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public boolean addProductToBill(Long billId, Long productId, double quantity, double measure, double price) {
        if (billId > 0) {
            Connection conn = ConnectionPool.getConnection();
            try (PreparedStatement statement = conn.prepareStatement("INSERT INTO bill_body (bill_id, product_id, quantity, measure, price) "
                    + "VALUES (?, ?, ?, ?,?)")) {
                conn.setAutoCommit(false);
                statement.setLong(1, billId);
                statement.setLong(2, productId);
                statement.setDouble(3, quantity);
                statement.setDouble(4, measure);
                statement.setDouble(5, price);
                checkIfEnoughAmount(productId,quantity,measure);
                statement.executeUpdate();
                conn.commit();
                return true;
            } catch (SQLException e) {
                logger.error(e);
            } catch (NotEnoughProductException e) {
                logger.error(e);
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void checkIfEnoughAmount(Long productId, Double quantity, Double measure) throws NotEnoughProductException {
        ProductDAO<Product> productDAO = DAOFactory.getProductDAO();
        Product product = productDAO.getById(productId);
        if(product.getQuantity()==-100500){
            if((product.getMeasure()-measure)<0){
                throw new NotEnoughProductException("not enough product");
            }
        }
        if(product.getMeasure()==-100500){
            if((product.getQuantity()-quantity)<0){
                throw new NotEnoughProductException("not enough product");
            }
        }

    }
}
