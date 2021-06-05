package com.ikea.wms.api.core.exceptions;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(String message, Throwable ex) {
        super(message, ex);
    }
}
