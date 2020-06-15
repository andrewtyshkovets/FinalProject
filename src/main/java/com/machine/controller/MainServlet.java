package com.machine.controller;

import com.machine.command.Command;
import com.machine.command.CommandFactory;
import com.machine.models.bill.Bill;
import com.machine.models.cashbox.CashBox;
import com.machine.models.user.User;
import com.machine.pages.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class MainServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        performTask(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        performTask(req, resp);
    }

    private void performTask(HttpServletRequest req, HttpServletResponse resp) {
       try {
            req.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url;
        if (req.getMethod().equals("GET")) {
            if (req.getServletPath().equals("/")) {
                url = "/WEB-INF/view/" + Page.INDEX.name().toLowerCase() + ".jsp";
            } else {
                url = "/WEB-INF/view/" + req.getServletPath() + ".jsp";
            }
        } else {
            CommandFactory factory = CommandFactory.getInstance();
            Command command = factory.getCommand(req);
            String path = command.execute(req, resp);
            url = "/WEB-INF/view/" + path.toLowerCase() + ".jsp";
        }
        try {
            req.getRequestDispatcher(url).forward(req, resp);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
