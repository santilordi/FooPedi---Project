package com.FooPedi.Proyecto.util;

public class InvalidOrderQuantityException extends OrderException {
    public InvalidOrderQuantityException(int quantity) {
        super("Cantidad del producto invalido: " + quantity + ". Debe ser mayor a cero.");
    }
}
