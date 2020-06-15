package com.machine.service;

import com.machine.DAO.CashBoxDAO;
import com.machine.DAO.DAOFactory;
import com.machine.DAO.ReportDAO;
import com.machine.models.cashbox.CashBox;
import com.machine.models.report.Report;

import java.time.LocalDateTime;

public class ReportService {

    public static Report makeXReport(long cashBoxId) {
        CashBoxDAO<CashBox> cashBoxDAO = DAOFactory.getCashBoxDAO();
        ReportDAO<Report> reportDAO = DAOFactory.getReportDAO();
        CashBox cashBox = cashBoxDAO.getById(cashBoxId);
        LocalDateTime startTime = cashBox.getStartDateTime();
        Double startMoney = cashBox.getStartMoney();
        LocalDateTime totalTime = cashBox.getCurrentDateTime();
        Double totalMoney = cashBox.getCurrentMoney();
        Double totalSales = totalMoney - startMoney;
        int type = 1;
        Report report = new Report(type, cashBoxId, startTime, startMoney, totalTime, totalMoney, totalSales);
        reportDAO.create(report);
        return report;
    }

    public static Report makeZReport(long cashBoxId){
        CashBoxDAO<CashBox> cashBoxDAO = DAOFactory.getCashBoxDAO();
        ReportDAO<Report> reportDAO = DAOFactory.getReportDAO();
        CashBox cashBox = cashBoxDAO.getById(cashBoxId);
        LocalDateTime startTime = cashBox.getStartDateTime();
        Double startMoney = cashBox.getStartMoney();
        LocalDateTime totalTime = cashBox.getFinishDateTime();
        Double totalMoney = cashBox.getFinishMoney();
        Double totalSales = totalMoney-startMoney;
        int type = 2;
        Report report = new Report(type,cashBoxId,startTime,startMoney,totalTime,totalMoney,totalSales);
        reportDAO.create(report);
        return report;
    }

}
