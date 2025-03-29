package com.FooPedi.Proyecto.util;

public class InsufficientStockException extends ProductException {
    public InsufficientStockException(String productName, int requestedQuantity, int availableStock) {
        super(String.format("Stock de producto insuficiente %s. Requerido: %d, Disponible: %d",
                productName, requestedQuantity, availableStock));
    }
}
