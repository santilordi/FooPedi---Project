package com.FooPedi.Proyecto.model;

import java.util.ArrayList;

public class Client {
    private int id;
    private String name;
    private String email;
    private String numTelephone;
    private ArrayList<Order> ordersClient;

    //Constructor
    public Client(String name, String email, String numTelephone) {
        this.name = name;
        this.email = email;
        this.numTelephone = numTelephone;
        this.ordersClient = new ArrayList<>();
    }

    //Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getNumTelephone() {
        return numTelephone;
    }

    public ArrayList<Order> getOrdersClient(){
        return new ArrayList<>(ordersClient);
    }

    //Setters
    public void setId(int id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setNumTelephone(String numTelephone){
        this.numTelephone = numTelephone;
    }

    public void addOrder(Order order) {
        if (ordersClient == null) {
            ordersClient = new ArrayList<>();
        }
        ordersClient.add(order);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", numTelephone='" + numTelephone + '\'' +
                '}';
    }
}
