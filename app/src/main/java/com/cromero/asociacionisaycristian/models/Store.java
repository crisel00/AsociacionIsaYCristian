package com.cromero.asociacionisaycristian.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Store implements Serializable {
    String idStore, nameStore;
    List<Product> products= new ArrayList<Product>();

    public Store() {
    }

    public Store(String idStore, String nameStore) {
        this.idStore = idStore;
        this.nameStore = nameStore;
    }

    public String getIdStore() {
        return idStore;
    }

    public String getNameStore() {
        return nameStore;
    }

    public void setNameStore(String nameStore) {
        this.nameStore = nameStore;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
