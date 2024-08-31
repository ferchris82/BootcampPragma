package com.chrisferdev.hus.configuration.exception.exceptionhandler;


import com.chrisferdev.hus.configuration.exception.BrandAlreadyExistsException;
import com.chrisferdev.hus.configuration.exception.CategoryAlreadyExistsException;
import com.chrisferdev.hus.configuration.exception.ProductException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    private static final String MESSAGE = "Message";

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleCategoryAlreadyExistsException(
            CategoryAlreadyExistsException categoryAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.CATEGORY_ALREADY_EXISTS.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(
            IllegalArgumentException illegalArgumentException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.INVALID_CATEGORY.getMessage()));
    }

    @ExceptionHandler(BrandAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleBrandAlreadyExistsException(
            BrandAlreadyExistsException brandAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.BRAND_ALREADY_EXISTS.getMessage()));
    }

    @ExceptionHandler(ProductException.class)
    public ResponseEntity<Map<String, String>> handleProductException(ProductException productException) {
        ExceptionResponse response;

        switch (productException.getErrorType()) {
            case ERROR_CATEGORY:
                response = ExceptionResponse.ERROR_CATEGORY;
                break;
            case TOO_MANY_CATEGORIES:
                response = ExceptionResponse.TOO_MANY_CATEGORIES;
                break;
            case NO_BRAND:
                response = ExceptionResponse.NO_BRAND;
                break;
            case PRODUCT_NOT_FOUND:
                response = ExceptionResponse.PRODUCT_NOT_FOUND;
                break;
            default:
                response = ExceptionResponse.INVALID_CATEGORY;
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, response.getMessage()));
    }

}
