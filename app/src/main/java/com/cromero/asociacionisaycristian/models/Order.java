package com.cromero.asociacionisaycristian.models;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {
    String orderId;
    ArrayList<OrderLine> orderLines = new ArrayList<>();
    Date orderDate;
    int status;
    String UserID = "";

    public Order() {
    }

    public Order(String orderId, ArrayList<OrderLine> orderLines, Date orderDate) {
        this.orderId = orderId;
        this.orderLines = orderLines;
        this.orderDate = orderDate;
        this.status=0;
    }

    public Order(String orderId, Date orderDate) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.status=0;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public ArrayList<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(ArrayList<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public void addLine(OrderLine line) {
        orderLines.add(line);
    }
}
