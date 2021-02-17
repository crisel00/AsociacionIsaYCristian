package com.cromero.asociacionisaycristian.models;

import java.io.Serializable;

public class OrderLine implements Serializable {
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
