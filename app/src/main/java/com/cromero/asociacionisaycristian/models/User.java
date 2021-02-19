package com.cromero.asociacionisaycristian.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private String userName, email, uid;
    private List<OrderLine> orders= new ArrayList<>();
    private float saldo;

    public User (){

    }
    public User(String email, String userName, String uid){
        this.email= email;
        this.userName=userName;
        this.uid=uid;
        this.saldo = 0;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public String getUid() {
        return uid;
    }

    public List<OrderLine> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderLine> orders) {
        this.orders = orders;
    }

    public float getSaldo() { return saldo; }
}
