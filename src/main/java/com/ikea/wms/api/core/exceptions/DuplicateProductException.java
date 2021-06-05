package com.ikea.wms.api.core.exceptions;

public class DuplicateProductException extends RuntimeException {

    public DuplicateProductException(String message) {
        super(message);
    }

    public DuplicateProductException(String message, Throwable ex) {
        super(message, ex);
    }
}
