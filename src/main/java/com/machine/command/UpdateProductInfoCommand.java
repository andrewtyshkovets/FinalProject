package com.machine.command;

import com.machine.models.product.Product;
import com.machine.models.user.User;
import com.machine.pages.Page;
import com.machine.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateProductInfoCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page;
        HttpSession session = request.getSession();
        Product product = (Product) session.getAttribute("product");
        if (product!=null){
            page = Page.UPDATE_PRODUCT_INFO.name();
        } else {
            String productIdS = request.getParameter("product_id");
            if(!productIdS.isEmpty()) {
                Long productId = Long.valueOf(productIdS);
                product = ProductService.getById(productId);
                if (product != null) {
                    session.setAttribute("product", product);
                    request.setAttribute("productName",product.getProductName());
                    request.setAttribute("productCode",product.getProductCode());
                    page = Page.UPDATE_PRODUCT_INFO.name();
                } else {
                    page = Page.PRODUCT_OBSERVER.name();
                }
            } else {
                page = Page.PRODUCT_OBSERVER.name();
            }
        }
        return page;
    }
}

