package com.machine.models.bill;

import com.machine.models.product.Product;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Bill {
    private long billId;
    private long creatorUserId;
    private int cashBoxId;
    private LocalDateTime currentDate;
    private double totalPrice;
    private boolean isCancelled;
    private Map<Integer,Double> productAmount;
    private Map<Integer,Double> productPrice;

    public Bill() {
        productPrice = new HashMap<>();
        productAmount = new HashMap<>();
    }

    public Bill(long billId) {
        this.billId = billId;
        productPrice = new HashMap<>();
        productAmount = new HashMap<>();
    }

    public long getBillId() {
        return billId;
    }

    public void setBillId(long billId) {
        this.billId = billId;
    }

    public long getCreatorUserId() {
        return creatorUserId;
    }

    public void setCreatorUserId(long creatorUserId) {
        this.creatorUserId = creatorUserId;
    }

    public LocalDateTime getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(LocalDateTime currentDate) {
        this.currentDate = currentDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }


    public int getCashBoxId() {
        return cashBoxId;
    }

    public void setCashBoxId(int cashBoxId) {
        this.cashBoxId = cashBoxId;
    }

    public Map<Integer, Double> getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(Map<Integer, Double> productAmount) {
        this.productAmount = productAmount;
    }

    public Map<Integer, Double> getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Map<Integer, Double> productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "billId=" + billId +
                ", creatorUserId=" + creatorUserId +
                ", cashBoxId=" + cashBoxId +
                ", currentDate=" + currentDate +
                ", totalPrice=" + totalPrice +
                ", isCancelled=" + isCancelled +
                ", productAmount=" + productAmount +
                ", productPrice=" + productPrice +
                '}';
    }
}
