package com.ikea.wms.api.core.exceptions;

public class ArticleNotFoundException extends RuntimeException {

    public ArticleNotFoundException(String message) {
        super(message);
    }

    public ArticleNotFoundException(String message, Throwable ex) {
        super(message, ex);
    }
}
