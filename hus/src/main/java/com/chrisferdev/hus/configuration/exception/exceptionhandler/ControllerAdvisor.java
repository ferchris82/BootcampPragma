package com.chrisferdev.hus.configuration.exception.exceptionhandler;


import com.chrisferdev.hus.configuration.exception.*;
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

    @ExceptionHandler(CategoryInvalidException.class)
    public ResponseEntity<Map<String, String>> handleCategoryInvalidException(
            CategoryInvalidException categoryException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.INVALID_CATEGORY.getMessage()));
    }

    @ExceptionHandler(BrandAlreadyException.class)
    public ResponseEntity<Map<String, String>> handleBrandInvalidException(
            BrandInvalidException brandAlreadyException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.BRAND_ALREADY_EXISTS.getMessage()));
    }

    @ExceptionHandler(BrandInvalidException.class)
    public ResponseEntity<Map<String, String>> handleBrandAlreadyExistsException(
            BrandInvalidException brandInvalidException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.INVALID_BRAND.getMessage()));
    }

    @ExceptionHandler(AddProductException.class)
    public ResponseEntity<Map<String, String>> handleProductWithoutCategory(
            AddProductException productException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.ERROR_CATEGORY.getMessage()));
    }

    @ExceptionHandler(FindProductException.class)
    public ResponseEntity<Map<String, String>> handleProductNoFound(
            FindProductException findProductException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.PRODUCT_NOT_FOUND.getMessage()));
    }

    @ExceptionHandler(FindProductByBrandException.class)
    public ResponseEntity<Map<String, String>> handleBrandNotFound(
            FindProductByBrandException findProductByBrandException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.PRODUCT_BRAND_NOT_FOUND.getMessage()));
    }

    @ExceptionHandler(ProductSaveException.class)
    public ResponseEntity<Map<String, String>> handleProductNotSave(
            ProductSaveException productSaveException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.PRODUCT_NOT_SAVE.getMessage()));
    }

    @ExceptionHandler(DocumentException.class)
    public ResponseEntity<Map<String, String>> handleDocumentException(
            DocumentException documentException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.ERROR_DOCUMENT_ID.getMessage()));
    }

    @ExceptionHandler(EmailException.class)
    public ResponseEntity<Map<String, String>> handleEmailException(
            EmailException emailException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.ERROR_EMAIL.getMessage()));
    }

    @ExceptionHandler(PhoneException.class)
    public ResponseEntity<Map<String, String>> handlePhoneException(
            PhoneException phoneException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.ERROR_PHONE.getMessage()));
    }

    @ExceptionHandler(BirthDathException.class)
    public ResponseEntity<Map<String, String>> handleBirthDateException(
            BirthDathException birthDathException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.ERROR_BIRTHDATE.getMessage()));
    }

}
