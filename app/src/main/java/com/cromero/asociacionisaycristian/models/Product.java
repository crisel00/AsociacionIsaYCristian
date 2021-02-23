package com.cromero.asociacionisaycristian.models;

import java.io.Serializable;

public class
Product implements Serializable {
    String description;
    Float stock, price;
    String idProduct;

    public Product() {
    }

    public Product(String idProduct, String description, Float price, Float stock) {
        this.idProduct = idProduct;
        this.description = description;
        this.price = price;
        this.stock= stock;
    }

    public Product(String idProduct, Float price) {
        this.idProduct = idProduct;
        this.price = price;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getStock() {
        return stock;
    }

    public void setStock(Float stock) {
        this.stock = stock;
    }
}
