package com.cromero.asociacionisaycristian.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {
    String orderId;
    List<OrderLine> orderLines;
    Date fechaPedido;

    public Order() {
    }

    public Order(String orderId, List<OrderLine> orderLines, Date fechaPedido) {
        this.orderId = orderId;
        this.orderLines = orderLines;
        this.fechaPedido = fechaPedido;
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

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }
}
