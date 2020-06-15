package com.machine.command;

import com.machine.models.product.Product;
import com.machine.pages.Page;
import com.machine.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ConfirmUpdatingCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page;
        HttpSession session = request.getSession();
        Product product = (Product) session.getAttribute("product");
        String newAmountS = request.getParameter("amount");
        String newPriceS = request.getParameter("price");
        if(!newAmountS.isEmpty()){
            Double newAmount= Double.valueOf(newAmountS);
            ProductService.setProductAmountOnWarehouse(product.getProductId(),newAmount);
        }
        if(!newPriceS.isEmpty()){
            Double newPrice = Double.valueOf(request.getParameter("price"));
            ProductService.setProductPriceOnWarehouse(product.getProductId(),newPrice);
        }
        session.setAttribute("product", null);
        page = Page.PRODUCT_OBSERVER.name();
        return page;
    }
}
