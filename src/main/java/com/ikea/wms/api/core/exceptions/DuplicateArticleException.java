package com.ikea.wms.api.core.exceptions;

public class DuplicateArticleException extends RuntimeException {

    public DuplicateArticleException(String message) {
        super(message);
    }

    public DuplicateArticleException(String message, Throwable ex) {
        super(message, ex);
    }
}
