package com.machine.command;

import com.machine.models.cashbox.CashBox;

import com.machine.pages.Page;
import com.machine.service.CashBoxService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CloseCashBoxCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = null;
        HttpSession session = request.getSession();
        CashBox cashBox = (CashBox) session.getAttribute("cashBox");
        if (cashBox != null) {
            CashBoxService.closeCashBoxSession(cashBox.getId());
            session.setAttribute("cashBox",null);
            page = Page.CASHIER.name();
        }
        return page;    }
}
