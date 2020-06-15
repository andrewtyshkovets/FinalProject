package com.machine.DAO;

import com.machine.DAO.DAOImplementation.*;
import com.machine.models.bill.Bill;
import com.machine.models.cashbox.CashBox;
import com.machine.models.product.Product;
import com.machine.models.report.Report;
import com.machine.models.user.User;

public class DAOFactory {

    public static UserDAO<User> getUserDAO() {
        return UserDAOImplementation.getInstance();
    }

    public static BillDAO<Bill> getBillDAO() {
        return BillDAOImplementation.getInstance();
    }

    public static CashBoxDAO<CashBox> getCashBoxDAO(){
        return CashBoxDAOImplementation.getInstance();
    }

    public static ProductDAO<Product> getProductDAO(){
        return ProductDAOImplementation.getInstance();
    }

    public static ReportDAO<Report> getReportDAO(){
        return ReportDAOImplementation.getInstance();
    }






}
