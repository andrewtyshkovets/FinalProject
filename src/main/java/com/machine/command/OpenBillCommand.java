package com.machine.command;

import com.machine.models.DateTimeHelper;
import com.machine.models.bill.Bill;
import com.machine.models.cashbox.CashBox;
import com.machine.models.user.User;
import com.machine.pages.Page;
import com.machine.service.BillService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OpenBillCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = null;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        CashBox cashBox = (CashBox) session.getAttribute("cashBox");
        Bill bill = (Bill) session.getAttribute("bill");

        // if bill is already have been opened
        if (bill != null) {
            session.setAttribute("bill", bill);
            request.setAttribute("billNumber", bill.getBillId());
            request.setAttribute("cashBoxNumber", cashBox.getCashBoxNumber());
            request.setAttribute("cashier", user.getFullName());
            request.setAttribute("date", DateTimeHelper.LocalDateTimeToString(bill.getCurrentDate()));
            request.setAttribute("productAmount", bill.getProductAmount());
            request.setAttribute("productPrice", bill.getProductPrice());
            request.setAttribute("total", bill.getTotalPrice());
            page = Page.BILL.name();
        } else {
            Long cashierId = user.getId();
            Long cashBoxId = cashBox.getId();
            Long billId = BillService.openBill(cashierId, cashBoxId.intValue());
            bill = BillService.findBillById(billId);
            session.setAttribute("bill", bill);
            request.setAttribute("billNumber", bill.getBillId());
            request.setAttribute("cashBoxNumber", cashBox.getCashBoxNumber());
            request.setAttribute("cashier", user.getFullName());
            request.setAttribute("date", DateTimeHelper.LocalDateTimeToString(bill.getCurrentDate()));
            request.setAttribute("productAmount", bill.getProductAmount());
            request.setAttribute("productPrice", bill.getProductPrice());
            request.setAttribute("total", bill.getTotalPrice());
            page = Page.BILL.name();
        }

        return page;
    }
}
