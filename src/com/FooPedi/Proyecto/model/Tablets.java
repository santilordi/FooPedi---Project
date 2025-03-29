package com.FooPedi.Proyecto.model;

public class Tablets extends Product{
    private double inches;

    public Tablets(String name, double price, int stock, double inches) {
        super(name, price, stock);
        this.inches = inches;
    }

    public double getPulgadas() {
        return inches;
    }

    // Método toString para representación de la tablet
    @Override
    public String toString() {
        return String.format("Tablet: %s - %d\" - $%.2f",
                name, inches, price);
    }
}
