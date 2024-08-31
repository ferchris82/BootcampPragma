package com.chrisferdev.pragmabackend.configuration.exception;

public class ProductException extends RuntimeException{

    public enum ErrorType {
        NO_CATEGORY,
        TOO_MANY_CATEGORIES,
        DUPLICATE_CATEGORIES,
        NO_BRAND
    }

    private final ErrorType errorType;

    public ProductException(ErrorType errorType, String message) {
        super(message);
        this.errorType = errorType;
    }

    public ErrorType getErrorType() {
        return errorType;
    }
}
