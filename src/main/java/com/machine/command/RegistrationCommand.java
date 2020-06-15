package com.machine.command;

import com.machine.pages.Page;
import com.machine.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String fullName = request.getParameter("full-name");
        String role = request.getParameter("role");
        Integer roleId = UserService.getRoleId(role);
        if (!username.isEmpty() && !password.isEmpty() && !fullName.isEmpty()) {
            if (UserService.checkIfExist(username)) {
                page = Page.REGISTRATION.name();
            } else {
                UserService.registerUser(username, password, fullName, roleId);
                page = Page.INDEX.name();
            }
        } else {
            page = Page.REGISTRATION.name();
        }
        return page;
    }
}
