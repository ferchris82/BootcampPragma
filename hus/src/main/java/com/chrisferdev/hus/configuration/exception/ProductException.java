package com.chrisferdev.hus.configuration.exception;

import lombok.Getter;

@Getter
public class ProductException extends RuntimeException{

    public enum ErrorType {
        NO_CATEGORY,
        TOO_MANY_CATEGORIES,
        NO_BRAND
    }

    private final ErrorType errorType;

    public ProductException(ErrorType errorType, String message) {
        super(message);
        this.errorType = errorType;
    }
}
