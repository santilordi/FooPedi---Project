package com.FooPedi.Proyecto.model;

import java.util.ArrayList;
import java.util.Date;

public class Order {
    private int id;
    private Client client;
    private ArrayList<OrderItem> orderItems;
    private Date dateOrder;
    private double totalCost;

    public Order(int id, Client client) {
        this.id = id;
        this.client = client;
    }

    //Getters
    public int getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public ArrayList<OrderItem> getOrderItems() {
        return orderItems;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public double getTotalCost() {
        return totalCost;
    }

    //Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setOrderItems(ArrayList<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}
