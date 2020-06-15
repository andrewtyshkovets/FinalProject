package com.machine.service;

import com.machine.DAO.BillDAO;
import com.machine.DAO.CashBoxDAO;
import com.machine.DAO.DAOFactory;
import com.machine.DAO.ProductDAO;
import com.machine.exceptions.NotEnoughProductException;
import com.machine.models.bill.Bill;
import com.machine.models.cashbox.CashBox;
import com.machine.models.product.Product;

import java.time.LocalDateTime;
import java.util.Map;

public class BillService {

    public static Bill findBillById(Long id) {
        BillDAO<Bill> billDAO = DAOFactory.getBillDAO();
        return billDAO.getById(id);
    }

    public static Long openBill(Long creatorId, int cashBoxId) {
        Bill bill = new Bill();
        bill.setCreatorUserId(creatorId);
        bill.setCashBoxId(cashBoxId);
        LocalDateTime currentDateTime = LocalDateTime.now();
        bill.setCurrentDate(currentDateTime);
        bill.setTotalPrice(0);
        bill.setCancelled(false);
        BillDAO<Bill> billDAO = DAOFactory.getBillDAO();
        Long billId = billDAO.create(bill);
        return billId;
    }

    public static boolean addProductToBillByName(Long billId, String productName, Double amount) throws NotEnoughProductException {
        double quantity = 0;
        double measure = 0;
        double price;
        ProductDAO<Product> productDAO = DAOFactory.getProductDAO();
        Product product = productDAO.getProductByName(productName);
        if (product.getQuantity() != -100500) {
            quantity = amount;
            price = quantity * product.getPricePerMeasureOrQuantity();
        } else {
            measure = amount;
            price = measure * product.getPricePerMeasureOrQuantity();
        }
        BillDAO<Bill> billDAO = DAOFactory.getBillDAO();
        return billDAO.addProductToBill(billId, product.getProductId(), quantity, measure, price);
    }

    public static boolean addProductToBillByCode(Long billId, Integer productCode, Double amount) throws NotEnoughProductException {
        double quantity = 0; //магія - не чіпати
        double measure = 0;
        double price;
        ProductDAO<Product> productDAO = DAOFactory.getProductDAO();
        Product product = productDAO.getProductByCode(productCode);
        if (product.getQuantity() != -100500) {
            quantity = amount;
            price = quantity * product.getPricePerMeasureOrQuantity();
        } else {
            measure = amount;
            price = measure * product.getPricePerMeasureOrQuantity();
        }
        BillDAO<Bill> billDAO = DAOFactory.getBillDAO();
        return billDAO.addProductToBill(billId, product.getProductId(), quantity, measure, price);
    }

    public static boolean closeBill(Long billId) {
        BillDAO<Bill> billDAO = DAOFactory.getBillDAO();
        CashBoxDAO<CashBox> cashBoxDAO = DAOFactory.getCashBoxDAO();
        ProductDAO<Product> productDAO = DAOFactory.getProductDAO();

        Bill bill = billDAO.getById(billId);
        CashBox cashBox = cashBoxDAO.getById((long) bill.getCashBoxId());
        Product product;

        Map<Integer, Double> productsAndPrices = bill.getProductPrice();
        Double totalPrice = 0.0;
        for (Map.Entry<Integer, Double> entry : productsAndPrices.entrySet()) {
            totalPrice = totalPrice + entry.getValue();
        }

        Map<Integer, Double> productsAndAmount = bill.getProductAmount();
        for (Map.Entry<Integer, Double> entry : productsAndAmount.entrySet()) {
            product = productDAO.getById(Long.valueOf(entry.getKey()));
            if(product.getQuantity()!=-100500){
                product.setQuantity(product.getQuantity()-entry.getValue());
            } else {
                product.setMeasure(product.getMeasure()-entry.getValue());
            }
            productDAO.update(product);
        }

        LocalDateTime localDateTime = LocalDateTime.now();
        bill.setCurrentDate(localDateTime);
        bill.setTotalPrice(totalPrice);
        cashBox.setCurrentDateTime(localDateTime);
        cashBox.setCurrentMoney(cashBox.getCurrentMoney() + bill.getTotalPrice());

        billDAO.update(bill);
        cashBoxDAO.update(cashBox);
        return true;
    }

    public static boolean cancelBill(Long billId) {
        BillDAO<Bill> billDAO = DAOFactory.getBillDAO();
        ProductDAO<Product> productDAO = DAOFactory.getProductDAO();
        CashBoxDAO<CashBox> cashBoxDAO = DAOFactory.getCashBoxDAO();
        if (billId > 0) {
            Bill bill = billDAO.getById(billId);
            if (!billDAO.ifCancelled(billId)) {
                Map<Integer, Double> amounts = bill.getProductAmount();
                Product product;
                for (Map.Entry<Integer, Double> entry : amounts.entrySet()) {
                    product = productDAO.getById(Long.valueOf(entry.getKey()));
                    if (product.getQuantity() == -100500) {
                        product.setMeasure(product.getMeasure() + entry.getValue());
                    } else {
                        product.setQuantity(product.getQuantity() + entry.getValue());
                    }
                    productDAO.update(product);
                }
                CashBox cashBox = cashBoxDAO.getById((long) bill.getCashBoxId());
                cashBox.setFinishMoney(cashBox.getFinishMoney() - bill.getTotalPrice());
                cashBoxDAO.update(cashBox);
                bill.setCancelled(true);
                billDAO.update(bill);
                return true;
            }
        }
        return false;
    }

}
