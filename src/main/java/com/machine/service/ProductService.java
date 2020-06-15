package com.machine.service;

import com.machine.DAO.DAOFactory;
import com.machine.DAO.ProductDAO;
import com.machine.models.product.Product;

import java.util.List;

public class ProductService {
    public static List<Product> getAll(){
        ProductDAO<Product> productDAO = DAOFactory.getProductDAO();
        List<Product> products = productDAO.getAll();
        return products;
    }
    public static Product getById(Long id){
        ProductDAO<Product> productDAO = DAOFactory.getProductDAO();
        return productDAO.getById(id);
    }
    public static Long addProductToWarehouse(int productCode, String name, Double quantity, Double measure,Double pricePerUnit){
        ProductDAO<Product> productDAO = DAOFactory.getProductDAO();
        Product product = new Product();
        product.setProductCode(productCode);
        product.setProductName(name);
        product.setPricePerMeasureOrQuantity(pricePerUnit);
        if(quantity==null) {
            product.setQuantity(-100500);
        } else {
            product.setQuantity(quantity);
        }
        if (measure==null){
            product.setMeasure(-100500);
        } else {
            product.setMeasure(measure);
        }
        return productDAO.create(product);
    }
    public static void setProductAmountOnWarehouse(Long productId,Double amount){
        ProductDAO<Product> productDAO = DAOFactory.getProductDAO();
        Product product = productDAO.getById(productId);
        if(product.getQuantity()!=-100500){
            product.setQuantity(amount);
        } else {
            product.setMeasure(amount);
        }
        productDAO.update(product);
    }
    public static void setProductPriceOnWarehouse(Long productId,Double price){
        ProductDAO<Product> productDAO = DAOFactory.getProductDAO();
        Product product = productDAO.getById(productId);
        product.setPricePerMeasureOrQuantity(price);
        productDAO.update(product);
    }
}
