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

public class CloseBillCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        CashBox cashBox =(CashBox)session.getAttribute("cashBox");
        User user = (User)session.getAttribute("user");
        Bill bill = (Bill) session.getAttribute("bill");
        request.setAttribute("billNumber", bill.getBillId());
        request.setAttribute("cashBoxNumber", cashBox.getCashBoxNumber());
        request.setAttribute("cashier", user.getFullName());
        request.setAttribute("date", DateTimeHelper.LocalDateTimeToString(bill.getCurrentDate()));
        request.setAttribute("productAmount", bill.getProductAmount());
        request.setAttribute("productPrice", bill.getProductPrice());
        BillService.closeBill(bill.getBillId());
        bill = BillService.findBillById(bill.getBillId());
        request.setAttribute("total", bill.getTotalPrice());
        request.setAttribute("bill",null);
        session.setAttribute("bill",null);
        return Page.BILL.name();
    }
}
