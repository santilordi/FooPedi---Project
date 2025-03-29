package com.FooPedi.Proyecto.model;

public class OrderItem {
    private Product product;
    private int amount;

    public OrderItem(Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    //Getters
    public Product getProduct() {
        return product;
    }

    public int getAmount() {
        return amount;
    }

    public double calculateSubtotal(){
        return product.getPrice() * amount;
    }
}
