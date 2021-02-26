package com.cromero.asociacionisaycristian.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {
    String orderId;
    List<OrderLine> orderLines;
    Date orderDate;
    int status;

    public Order() {
    }

    public Order(String orderId, List<OrderLine> orderLines, Date orderDate) {
        this.orderId = orderId;
        this.orderLines = orderLines;
        this.orderDate = orderDate;
        this.status=0;
    }
    public Order(String orderId, Date orderDate) {
        this.orderId = orderId;
        this.orderLines = orderLines;
        this.orderDate = orderDate;
        this.status=0;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
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
}
