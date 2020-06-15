package com.machine.command;

import com.machine.models.DateTimeHelper;
import com.machine.models.cashbox.CashBox;
import com.machine.models.report.Report;
import com.machine.models.user.User;
import com.machine.pages.Page;
import com.machine.service.CashBoxService;
import com.machine.service.ReportService;
import com.machine.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MakeXCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page;
        String cashBoxIdS = request.getParameter("cashBox_id");
        if (!cashBoxIdS.isEmpty()) {
            Long cashBoxId = Long.valueOf(cashBoxIdS);
            CashBox cashBox = CashBoxService.getById(cashBoxId);
            User user = UserService.getById(cashBox.getCashierId());
            Report report = ReportService.makeXReport(cashBoxId);
            request.setAttribute("reportId",report.getId());
            String type;
            if(report.getTypeId()==1){
                type = "X";
            } else {
                type = "Z";
            }
            request.setAttribute("type",type);
            request.setAttribute("cashBoxId",report.getCashBoxId());
            request.setAttribute("cashier",user.getId());
            request.setAttribute("startTime", DateTimeHelper.LocalDateTimeToString(report.getStartTime()));
            request.setAttribute("startMoney",report.getStartMoney());
            request.setAttribute("currentTime", DateTimeHelper.LocalDateTimeToString(report.getTotalTime()));
            request.setAttribute("currentMoney",report.getTotalMoney());
            request.setAttribute("totalSales",report.getSumOfSales());
            page = Page.MAKE_X.name();
        } else {
            page = Page.SENIOR_CASHIER.name();
        }
        return page;
    }
}
