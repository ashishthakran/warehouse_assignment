package com.ikea.wms.api.core.exceptions;

public class InsufficientInventoryException extends RuntimeException {

    public InsufficientInventoryException(String message) {
        super(message);
    }

    public InsufficientInventoryException(String message, Throwable ex) {
        super(message, ex);
    }
}
