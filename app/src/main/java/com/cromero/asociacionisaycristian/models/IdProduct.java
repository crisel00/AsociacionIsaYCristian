package com.cromero.asociacionisaycristian.models;

public class IdProduct {
    String nameStore;
    String idStore;

    public IdProduct() {
    }

    public IdProduct(String nameStore, String idStore) {
        this.nameStore = nameStore;
        this.idStore = idStore;
    }

    public String getNameStore() {
        return nameStore;
    }


    public String getIdStore() {
        return idStore;
    }
}
