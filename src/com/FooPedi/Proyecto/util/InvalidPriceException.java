package com.FooPedi.Proyecto.util;

public class InvalidPriceException extends ProductException {
    public InvalidPriceException(double price) {
        super("Precio invalido: " + price + ". El precio deberia ser mayor a cero.");
    }
}
