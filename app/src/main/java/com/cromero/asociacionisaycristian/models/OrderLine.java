package com.cromero.asociacionisaycristian.models;

public class OrderLine {
    Product product;
    Float amount;

    public OrderLine() {
    }

    public OrderLine(Product product, Float amount) {
        this.product = product;
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }
}
