package com.cromero.asociacionisaycristian.models;

import java.io.Serializable;

public class
Product implements Serializable {
    String description;
    Float stock, price;
    IdProduct idProduct; //productName and storeId

    public Product() {
    }

    public Product(IdProduct idProduct, String description, float price, Float stock) {
        this.idProduct = idProduct;
        this.description = description;
        this.price = price;
        this.stock= stock;
    }

    public Product(IdProduct idProduct, float price) {
        this.idProduct = idProduct;
        this.price = price;
    }

    public IdProduct getIdProduct() {
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
