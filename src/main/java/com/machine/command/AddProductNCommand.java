package com.machine.command;

import com.machine.exceptions.NotEnoughProductException;
import com.machine.models.DateTimeHelper;
import com.machine.models.bill.Bill;
import com.machine.models.cashbox.CashBox;
import com.machine.models.user.User;
import com.machine.pages.Page;
import com.machine.service.BillService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddProductNCommand implements Command {
    private static Logger logger = Logger.getLogger(AddProductCCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = null;
        HttpSession session = request.getSession();
        Bill bill = (Bill) session.getAttribute("bill");
        CashBox cashBox =(CashBox)session.getAttribute("cashBox");
        User user = (User)session.getAttribute("user");
        Long billId = bill.getBillId();
        String amountS = request.getParameter("amount");
        String productName = request.getParameter("productName");
        if (!productName.isEmpty() && !amountS.isEmpty()) {
            Double amount = Double.valueOf(amountS);
            try {
                BillService.addProductToBillByName(billId, productName, amount);
                bill = BillService.findBillById(billId);
                session.setAttribute("bill", bill);
                request.setAttribute("billNumber", bill.getBillId());
                request.setAttribute("cashBoxNumber", cashBox.getCashBoxNumber());
                request.setAttribute("cashier", user.getFullName());
                request.setAttribute("date", DateTimeHelper.LocalDateTimeToString(bill.getCurrentDate()));
                request.setAttribute("productAmount", bill.getProductAmount());
                request.setAttribute("productPrice", bill.getProductPrice());
                request.setAttribute("total", bill.getTotalPrice());
            } catch (NotEnoughProductException e) {
                logger.error(e);
                return Page.ADD_PRODUCT_TO_BILL_N.name();
            }
            page = Page.BILL.name();
        } else {
            page = Page.ADD_PRODUCT_TO_BILL_N.name();
        }
        return page;
    }
}
