package com.FooPedi.Proyecto.model;

import java.util.ArrayList;

public class Client {
    private final int id;
    private String name;
    private String email;
    private String numTelephone;
    private ArrayList<Order> ordersClient;

    //Constructor
    public Client(int id, String name, String email, String numTelephone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.numTelephone = numTelephone;
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
        return ordersClient;
    }

    //Setters
    public void setName(String name){
        this.name = name;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setNumTelephone(String numTelephone){
        this.numTelephone = numTelephone;
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
