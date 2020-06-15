package com.machine.command;

import com.machine.models.cashbox.CashBox;
import com.machine.models.user.User;
import com.machine.pages.Page;
import com.machine.service.CashBoxService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OpenCashBoxCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = null;
        if (request.getParameter("number_of_cashbox").equals("") || request.getParameter("start_money").equals("")) {
            page = Page.CASHIER.name();
            return page;
        }
        HttpSession session = request.getSession();
        CashBox cashBox = (CashBox) session.getAttribute("cashBox");
        User user = (User) session.getAttribute("user");
        Long userId = user.getId();

        // if cashBox is have already opened
        if (cashBox != null) {
            page = Page.CASHBOX.name();
        } else {
            String cashBoxNumberS = request.getParameter("number_of_cashbox");
            String startMoneyS = request.getParameter("start_money");
            if (!cashBoxNumberS.isEmpty() && !startMoneyS.isEmpty()) {
                Integer cashBoxNumber = Integer.valueOf(cashBoxNumberS);
                cashBox = CashBoxService.getByNumber(cashBoxNumber);
                if (cashBox != null) {
                    Double startMoney = Double.valueOf(startMoneyS);
                    Long cashBoxId = CashBoxService.openCashBoxSession(cashBoxNumber, userId, startMoney);
                    cashBox = CashBoxService.getById(cashBoxId);
                    session.setAttribute("cashBox", cashBox);
                    page = Page.CASHBOX.name();
                } else {
                    page = Page.CASHIER.name();
                }
            } else {
                page = Page.CASHIER.name();
            }
        }
        return page;
    }
}
