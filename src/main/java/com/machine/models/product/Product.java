package com.machine.models.product;

public class Product {
    private long productId;
    private int productCode;
    private String productName;
    private double quantity;
    private double measure;
    private double pricePerMeasureOrQuantity;

    public Product() {
    }

    public Product(long productId) {
        this.productId = productId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getMeasure() {
        return measure;
    }

    public void setMeasure(double measure) {
        this.measure = measure;
    }

    public double getPricePerMeasureOrQuantity() {
        return pricePerMeasureOrQuantity;
    }

    public void setPricePerMeasureOrQuantity(double pricePerMeasureOrQuantity) {
        this.pricePerMeasureOrQuantity = pricePerMeasureOrQuantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productCode=" + productCode +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", measure=" + measure +
                ", pricePerMeasureOrQuantity=" + pricePerMeasureOrQuantity +
                '}';
    }
}
