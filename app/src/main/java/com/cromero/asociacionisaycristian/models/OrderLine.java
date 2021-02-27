package com.cromero.asociacionisaycristian.models;

import java.io.Serializable;

public class OrderLine implements Serializable {
    Product product;
    Float amount;
    Store store;

    public OrderLine() {
    }

    public OrderLine(Product product, Float amount, Store store) {
        this.product = product;
        this.amount = amount;
        this.store = store;
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

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}