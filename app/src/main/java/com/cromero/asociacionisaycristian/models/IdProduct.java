package com.cromero.asociacionisaycristian.models;

import java.io.Serializable;

public class IdProduct implements Serializable {
    String nameProduct;
    String idStore;

    public IdProduct() {
    }

    public IdProduct(String nameProduct, String idStore) {
        this.nameProduct = nameProduct;
        this.idStore = idStore;
    }

    public String getNameProduct() {
        return nameProduct;
    }


    public String getIdStore() {
        return idStore;
    }
}
