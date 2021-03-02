package com.cromero.asociacionisaycristian.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private String userName, email, uid;
    private Order cart;
    private List<Order> orders= new ArrayList<>();
    private float balance;
    private boolean isManager;

    public User (){

    }
    public User(String email, String userName, String uid){
        this.email= email;
        this.userName=userName;
        this.uid=uid;
        this.balance = 0;
        isManager=false;
        cart=new Order();
    }

    public void addOrder(Order order){
            orders.add(order);
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

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public float getBalance() { return balance; }

    public Order getCart() {
        return cart;
    }

    public void setCart(Order cart) {
        this.cart = cart;
    }

    public void addProductToCart(OrderLine orderLine) {
        cart.addLine(orderLine);
    }

    public  void editOrder(Order order){
        List<Order> editedOrders= new ArrayList<>();
        //The order list of the store is iterated
        for (int i = 0; i<orders.size(); i++){
            Order thisOrder = orders.get(i);

            //If a order is the order being edited (same id) it is replaced by the new order
            if (thisOrder.getOrderId() == order.getOrderId()){
                thisOrder=order;
            }
            //The products are added to the new list
            editedOrders.add(thisOrder);
        }
        //Finally the list is added to the store
        orders.clear();
        orders=editedOrders;
    }

    public boolean isManager(){return isManager;}
    public void  makeManager(){isManager=true;}

}
