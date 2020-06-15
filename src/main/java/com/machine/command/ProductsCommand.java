package com.machine.command;

import com.machine.models.product.Product;
import com.machine.pages.Page;
import com.machine.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ProductsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Product> products = ProductService.getAll();
        System.out.println(products);
        request.setAttribute("products",products);
        return Page.PRODUCTS.name();
    }
}
