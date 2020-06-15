package com.machine.service;

import com.machine.DAO.CashBoxDAO;
import com.machine.DAO.DAOFactory;
import com.machine.models.cashbox.CashBox;

import java.time.LocalDateTime;

public class CashBoxService {
    public static long openCashBoxSession(int cashBoxNumber,long userId,double startMoney){
        CashBoxDAO<CashBox> cashBoxDAO = DAOFactory.getCashBoxDAO();
        CashBox cashBox = new CashBox();
        cashBox.setCashBoxNumber(cashBoxNumber);
        cashBox.setCashierId(userId);
        cashBox.setStartMoney(startMoney);
        LocalDateTime dateTime = LocalDateTime.now();
        cashBox.setStartDateTime(dateTime);
        return cashBoxDAO.open(cashBox);
    }
    public static void closeCashBoxSession(long cashBoxId){
        CashBoxDAO<CashBox> cashBoxDAO = DAOFactory.getCashBoxDAO();
        CashBox cashBox = cashBoxDAO.getById(cashBoxId);
        cashBox.setFinishMoney(cashBox.getCurrentMoney());
        LocalDateTime localDateTime = LocalDateTime.now();
        cashBox.setFinishDateTime(localDateTime);
        cashBoxDAO.update(cashBox);
        ReportService.makeZReport(cashBoxId);
    }
    public static long registerNewCashBox(int cashBoxNumber){
        CashBoxDAO<CashBox> cashBoxDAO = DAOFactory.getCashBoxDAO();
        CashBox cashBox = new CashBox();
        cashBox.setCashBoxNumber(cashBoxNumber);
        cashBox.setStartMoney(0);
        cashBox.setCashierId(1);
        LocalDateTime dateTime = LocalDateTime.now();
        cashBox.setStartDateTime(dateTime);
        return  cashBoxDAO.create(cashBox);
    }
    public static CashBox getByNumber(Integer number){
        CashBoxDAO<CashBox> cashBoxDAO = DAOFactory.getCashBoxDAO();
        return cashBoxDAO.getByNumber(number);
    }
    public static CashBox getById(Long number){
        CashBoxDAO<CashBox> cashBoxDAO = DAOFactory.getCashBoxDAO();
        return cashBoxDAO.getById(number);
    }

}
