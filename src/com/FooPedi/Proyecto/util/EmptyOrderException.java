package com.FooPedi.Proyecto.util;

public class EmptyOrderException extends OrderException {
    public EmptyOrderException() {
        super("No se puede crear una orden sin productos.");
    }
}
