package com.machine.command;

import com.machine.pages.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddByProductNameCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return Page.ADD_PRODUCT_TO_BILL_N.name();
    }
}
