package com.machine.command;

import com.machine.DAO.DAOImplementation.ProductDAOImplementation;
import com.machine.models.product.Product;
import com.machine.pages.Page;
import com.machine.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConfirmAddingProductCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page;
        if (!request.getParameter("code").isEmpty() && !request.getParameter("name").isEmpty() &&
                !request.getParameter("quantity").isEmpty() && !request.getParameter("measure").isEmpty() &&
                !request.getParameter("price").isEmpty()) {
            Integer productCode = Integer.valueOf(request.getParameter("code"));
            String productName = request.getParameter("name");
            Double quantity = Double.valueOf(request.getParameter("quantity"));
            Double measure = Double.valueOf(request.getParameter("measure"));
            Double price = Double.valueOf(request.getParameter("price"));
            if (quantity != 0 && measure != 0) {
                page = Page.ADD_PRODUCT_TO_WAREHOUSE.name();
            } else {
                ProductService.addProductToWarehouse(productCode, productName, quantity, measure, price);
                page = Page.PRODUCT_OBSERVER.name();
            }
        } else {
            page = Page.ADD_PRODUCT_TO_WAREHOUSE.name();
        }
        return page;
    }
}
