package com.FooPedi.Proyecto.model;

public class Computer extends Product{
    private double inches;
    private int ramGB;

    public Computer(String name, double price, int stock, double inches, int ramGB) {
        super(name, price, stock);
        this.inches = inches;
        this.ramGB = ramGB;
    }

    public double getInches() {
        return inches;
    }

    public int getRamGB() {
        return ramGB;
    }

    @Override
    public String toString() {
        return String.format("Computadora: %s - %d\" - %dGB RAM - $%.2f",
                name, inches, ramGB, price);
    }


}
