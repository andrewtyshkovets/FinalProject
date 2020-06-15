package com.machine.command;

import com.machine.models.user.User;
import com.machine.pages.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BackToUserCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = null;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            String role = user.getRole().name();
            if(role.equals("CASHIER")){
                page = Page.CASHIER.name();
            } else if (role.equals("SENIOR_CASHIER")){
                page = Page.SENIOR_CASHIER.name();
            } else if (role.equals("PRODUCT_OBSERVER")){
                page = Page.PRODUCT_OBSERVER.name();
            } else {
                page = Page.INDEX.name();
            }
        }
        return page;
    }
}
