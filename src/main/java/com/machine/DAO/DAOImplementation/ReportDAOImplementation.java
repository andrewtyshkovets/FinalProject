package com.machine.DAO.DAOImplementation;

import com.machine.DAO.ConnectionPool;
import com.machine.DAO.ReportDAO;
import com.machine.models.DateTimeHelper;
import com.machine.models.report.Report;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportDAOImplementation implements ReportDAO<Report> {


    private static ReportDAOImplementation instance;
    private static Logger logger = Logger.getLogger(ReportDAOImplementation.class);

    private ReportDAOImplementation() {
    }

    public static ReportDAO<Report> getInstance() {
        if (instance == null) {
            instance = new ReportDAOImplementation();
        }
        return instance;
    }

    @Override
    public List<Report> getAll() {
        List<Report> reports = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM report");
            while (resultSet.next()) {
                Report report = new Report();
                report.setId(resultSet.getInt("report_id"));
                report.setTypeId(resultSet.getInt("report_type_id"));
                report.setCashBoxId(resultSet.getLong("cashbox_id"));
                report.setStartMoney(resultSet.getDouble("start_money"));
                report.setStartTime(DateTimeHelper.StringToLocalDateTime(resultSet.getString("start_time")));
                report.setTotalMoney(resultSet.getDouble("total_money"));
                report.setTotalTime(DateTimeHelper.StringToLocalDateTime(resultSet.getString("total_time")));
                report.setSumOfSales(resultSet.getDouble("sum_of_sales"));
                reports.add(report);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return reports;
    }

    @Override
    public void update(Report entity) {
        if (entity != null) {
            try (Connection connection = ConnectionPool.getConnection();
                 PreparedStatement statement = connection.prepareStatement("UPDATE report SET report_type_id=?, cashbox_id=?, start_time=?, start_money=?,total_time=?,total_money=?, sum_of_sales=? WHERE report_id=?")) {
                statement.setInt(1, entity.getTypeId());
                statement.setLong(2, entity.getCashBoxId());
                statement.setString(3, DateTimeHelper.LocalDateTimeToString(entity.getStartTime()));
                statement.setDouble(4, entity.getStartMoney());
                statement.setString(5, DateTimeHelper.LocalDateTimeToString(entity.getTotalTime()));
                statement.setDouble(6, entity.getTotalMoney());
                statement.setDouble(7, entity.getSumOfSales());
                statement.setLong(8,entity.getId());
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
                 PreparedStatement statement = connection.prepareStatement("DELETE FROM report WHERE report_id = ?")) {
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
    public Long create(Report entity) {
        if (entity != null) {
            try (Connection connection = ConnectionPool.getConnection();
                 PreparedStatement statement = connection.prepareStatement("INSERT INTO report "
                         + "(report_type_id, cashbox_id, start_time, start_money, total_time, total_money,sum_of_sales) VALUES (?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
                statement.setLong(1, entity.getTypeId());
                statement.setLong(2, entity.getCashBoxId());
                statement.setString(3, DateTimeHelper.LocalDateTimeToString(entity.getStartTime()));
                statement.setDouble(4, entity.getStartMoney());
                statement.setString(5, DateTimeHelper.LocalDateTimeToString(entity.getTotalTime()));
                statement.setDouble(6, entity.getTotalMoney());
                statement.setDouble(7, entity.getSumOfSales());
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
    public Report getById(Long key) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("SELECT * FROM report WHERE report_id = ?")) {
            statement.setLong(1, key);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Report report = new Report();
                report.setId(resultSet.getInt("report_id"));
                report.setTypeId(resultSet.getInt("report_type_id"));
                report.setCashBoxId(resultSet.getLong("cashbox_id"));
                report.setStartMoney(resultSet.getDouble("start_money"));
                report.setStartTime(DateTimeHelper.StringToLocalDateTime(resultSet.getString("start_time")));
                report.setTotalMoney(resultSet.getDouble("total_money"));
                report.setTotalTime(DateTimeHelper.StringToLocalDateTime(resultSet.getString("total_time")));
                report.setSumOfSales(resultSet.getDouble("sum_of_sales"));
                return report;
            }
        } catch (SQLException e) {
            logger.error("Error while searching for cashBox", e);
        }
        return null;
    }
}
