package com.chrisferdev.hus.configuration.exception;

import lombok.Getter;

@Getter
public class ProductException extends RuntimeException {

    // Enum para definir los tipos de error
    public enum ErrorType {
        ERROR_CATEGORY,
        TOO_MANY_CATEGORIES,
        NO_BRAND
    }

    // Campo para almacenar el tipo de error
    private final ErrorType errorType;

    // Constructor por defecto
    public ProductException() {
        super();
        this.errorType = null;
    }

    // Constructor con mensaje y tipo de error
    public ProductException(ErrorType errorType) {
        super(errorType.name());
        this.errorType = errorType;
    }

    // Constructor con mensaje, tipo de error y causa
    public ProductException(ErrorType errorType, String message) {
        super(message);
        this.errorType = errorType;
    }

    // Constructor con mensaje, causa y tipo de error
    public ProductException(ErrorType errorType, String message, Throwable cause) {
        super(message, cause);
        this.errorType = errorType;
    }

    // Constructor con causa y tipo de error
    public ProductException(ErrorType errorType, Throwable cause) {
        super(cause);
        this.errorType = errorType;
    }
}
