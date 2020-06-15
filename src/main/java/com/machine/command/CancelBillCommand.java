package com.machine.command;

import com.machine.pages.Page;
import com.machine.service.BillService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CancelBillCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = null;
        String billIdS = request.getParameter("bill_id");
        if (!billIdS.isEmpty()) {
            Long billId = Long.valueOf(billIdS);
            BillService.cancelBill(billId);
            page = Page.SENIOR_CASHIER.name();
        } else {
            page = Page.SENIOR_CASHIER.name();
        }
        return page;
    }
}
