package com.machine.DAO.DAOImplementation;

import com.machine.DAO.ConnectionPool;
import com.machine.DAO.CashBoxDAO;
import com.machine.models.DateTimeHelper;
import com.machine.models.cashbox.CashBox;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CashBoxDAOImplementation implements CashBoxDAO<CashBox> {
    private static CashBoxDAOImplementation instance;
    private static Logger logger = Logger.getLogger(CashBoxDAOImplementation.class);

    private CashBoxDAOImplementation() {
    }

    public static CashBoxDAO<CashBox> getInstance() {
        if (instance == null) {
            instance = new CashBoxDAOImplementation();
        }
        return instance;
    }


    @Override
    public Long open(CashBox entity) {
        if (entity != null) {
            try (Connection connection = ConnectionPool.getConnection();
                 PreparedStatement statement = connection.prepareStatement("INSERT INTO cashBox "
                         + "(cashbox_number,user_id, start_money, start_time, current_money, calling_time,end_money,end_time) VALUES (?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
                statement.setInt(1, entity.getCashBoxNumber());
                statement.setLong(2, entity.getCashierId());
                statement.setDouble(3, entity.getStartMoney());
                statement.setString(4, DateTimeHelper.LocalDateTimeToString(entity.getStartDateTime()));
                statement.setDouble(5, entity.getStartMoney());
                statement.setString(6, DateTimeHelper.LocalDateTimeToString(entity.getStartDateTime()));
                statement.setDouble(7, entity.getStartMoney());
                statement.setString(8, DateTimeHelper.LocalDateTimeToString(entity.getStartDateTime()));
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
    public List<CashBox> getAll() {
        List<CashBox> cashBoxes = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM cashbox");
            while (resultSet.next()) {
                CashBox cashBox = new CashBox();
                cashBox.setId(resultSet.getLong("cashbox_id"));
                cashBox.setCashBoxNumber(resultSet.getInt("cashbox_number"));
                cashBox.setCashierId(resultSet.getLong("user_id"));
                cashBox.setStartMoney(resultSet.getDouble("start_money"));
                cashBox.setStartDateTime(DateTimeHelper.StringToLocalDateTime(resultSet.getString("start_time")));
                cashBox.setCurrentMoney(resultSet.getDouble("current_money"));
                cashBox.setCurrentDateTime(DateTimeHelper.StringToLocalDateTime(resultSet.getString("calling_time")));
                cashBox.setFinishMoney(resultSet.getDouble("end_money"));
                cashBox.setFinishDateTime(DateTimeHelper.StringToLocalDateTime(resultSet.getString("end_time")));
                cashBoxes.add(cashBox);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return cashBoxes;
    }

    @Override
    public void update(CashBox entity) {
        if (entity != null) {
            try (Connection connection = ConnectionPool.getConnection();
                 PreparedStatement statement = connection.prepareStatement("UPDATE cashbox SET user_id=?, start_money=?, start_time=?, current_money=?,calling_time=?,end_money=?, end_time=?,cashbox_number=? WHERE cashbox_id=?")) {
                statement.setLong(1, entity.getCashierId());
                statement.setDouble(2, entity.getStartMoney());
                statement.setString(3, DateTimeHelper.LocalDateTimeToString(entity.getStartDateTime()));
                statement.setDouble(4, entity.getCurrentMoney());
                statement.setString(5, DateTimeHelper.LocalDateTimeToString(entity.getCurrentDateTime()));
                statement.setDouble(6, entity.getFinishMoney());
                statement.setString(7, DateTimeHelper.LocalDateTimeToString(entity.getFinishDateTime()));
                statement.setInt(8, entity.getCashBoxNumber());
                statement.setLong(9,entity.getId());
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
                 PreparedStatement statement = connection.prepareStatement("DELETE FROM cashbox WHERE cashbox_id = ?")) {
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
    public Long create(CashBox entity) {
        if (entity != null) {
            try (Connection connection = ConnectionPool.getConnection();
                 PreparedStatement statement = connection.prepareStatement("INSERT INTO cashBox "
                         + "(user_id, start_money, start_time, current_money, calling_time,end_money,end_time,cashbox_number) VALUES (?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
                statement.setLong(1, entity.getCashierId());
                statement.setDouble(2, entity.getStartMoney());
                statement.setString(3, DateTimeHelper.LocalDateTimeToString(entity.getStartDateTime()));
                statement.setDouble(4, entity.getStartMoney());
                statement.setString(5, DateTimeHelper.LocalDateTimeToString(entity.getStartDateTime()));
                statement.setDouble(6, entity.getStartMoney());
                statement.setString(7, DateTimeHelper.LocalDateTimeToString(entity.getStartDateTime()));
                statement.setInt(8,entity.getCashBoxNumber());
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
    public CashBox getById(Long key) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("SELECT * FROM cashbox WHERE cashbox_id = ?")) {
            statement.setLong(1, key);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                CashBox cashBox = new CashBox();
                cashBox.setId(resultSet.getInt("cashbox_id"));
                cashBox.setCashBoxNumber(resultSet.getInt("cashBox_number"));
                cashBox.setCashierId(resultSet.getLong("user_id"));
                cashBox.setStartMoney(resultSet.getDouble("start_money"));
                cashBox.setStartDateTime(DateTimeHelper.StringToLocalDateTime(resultSet.getString("start_time")));
                cashBox.setCurrentMoney(resultSet.getDouble("current_money"));
                cashBox.setCurrentDateTime(DateTimeHelper.StringToLocalDateTime(resultSet.getString("calling_time")));
                cashBox.setFinishMoney(resultSet.getDouble("end_money"));
                cashBox.setFinishDateTime(DateTimeHelper.StringToLocalDateTime(resultSet.getString("end_time")));
                return cashBox;
            }
        } catch (SQLException e) {
            logger.error("Error while searching for cashBox", e);
        }
        return null;
    }

    @Override
    public CashBox getByNumber(Integer number){
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("SELECT * FROM cashbox WHERE cashbox_number = ?")) {
            statement.setLong(1, number);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                CashBox cashBox = new CashBox();
                cashBox.setId(resultSet.getInt("cashbox_id"));
                cashBox.setCashBoxNumber(resultSet.getInt("cashBox_number"));
                cashBox.setCashierId(resultSet.getLong("user_id"));
                cashBox.setStartMoney(resultSet.getDouble("start_money"));
                cashBox.setStartDateTime(DateTimeHelper.StringToLocalDateTime(resultSet.getString("start_time")));
                cashBox.setCurrentMoney(resultSet.getDouble("current_money"));
                cashBox.setCurrentDateTime(DateTimeHelper.StringToLocalDateTime(resultSet.getString("calling_time")));
                cashBox.setFinishMoney(resultSet.getDouble("end_money"));
                cashBox.setFinishDateTime(DateTimeHelper.StringToLocalDateTime(resultSet.getString("end_time")));
                return cashBox;
            }
        } catch (SQLException e) {
            logger.error("Error while searching for cashBox", e);
        }
        return null;
    }
}
