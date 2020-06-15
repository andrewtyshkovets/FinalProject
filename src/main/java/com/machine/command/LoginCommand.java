package com.machine.command;

import com.machine.models.user.User;
import com.machine.pages.Page;
import com.machine.service.UserService;
import com.machine.service.Utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.SortedMap;

public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = null;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // if user is already logged
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
        } else {
             String login = request.getParameter("username");
             String password = request.getParameter("password");
            if (login != null && password != null && !login.isEmpty() && !password.isEmpty()) {
                user = UserService.findUser(login,password);
            }
            if (user != null && password != null) {
                session.setAttribute("user", user);
                session.setAttribute("role",user.getRole());
                session.setAttribute("ID",user.getId());
                if(user.getRole().name().equals("CASHIER")){
                    page = Page.CASHIER.name();
                } else if (user.getRole().name().equals("SENIOR_CASHIER")){
                    page = Page.SENIOR_CASHIER.name();
                } else if (user.getRole().name().equals("PRODUCT_OBSERVER")){
                    page = Page.PRODUCT_OBSERVER.name();
                } else {
                    page = Page.INDEX.name();
                }
            } else {
                /*String error = Utils.getMessage("incorrect_pass_or_login",
                        request);
                request.setAttribute("invalidLogin", error);*/
                page = Page.INDEX.name();
            }
        }
        return page;
    }
}

