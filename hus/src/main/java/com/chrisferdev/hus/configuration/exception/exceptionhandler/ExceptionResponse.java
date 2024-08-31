package com.chrisferdev.hus.configuration.exception.exceptionhandler;

import lombok.Getter;

@Getter
public enum ExceptionResponse {
    CATEGORY_ALREADY_EXISTS("Ya hay una categoría con ese nombre"),
    INVALID_CATEGORY("El nombre o la descripción de la categoría no puede estar vacía"),
    BRAND_ALREADY_EXISTS("Ya hay una marca con ese nombre"),
    INVALID_BRAND("El nombre o la descripción de la marca no puede estar vacía"),
    ERROR_CATEGORY("El producto debe tener al menós una categoría y máximo 3 asociadas"),
    TOO_MANY_CATEGORIES("El producto debe tener máximo tres categorías asociadas"),
    DUPLICATE_CATEGORIES("El producto no debe tener una categoría duplicada"),
    NO_BRAND("El producto debe tener una marca asociada");

    private String message;

    ExceptionResponse(String message) {
        this.message = message;
    }


}
