package com.chrisferdev.pragmabackend.domain.spi.output;

import com.chrisferdev.pragmabackend.domain.model.PaginatedResult;
import com.chrisferdev.pragmabackend.domain.model.Product;

public interface IProductPersistencePort {

    Product saveProduct(Product product);
    PaginatedResult<Product> findAllProducts(String sortOrder, int page, int size);
    PaginatedResult<Product> findProductsByName(String name, String sortOrder, int page, int size);

}
