package com.chrisferdev.hus.domain.spi.output;


import com.chrisferdev.hus.domain.model.PaginatedResult;
import com.chrisferdev.hus.domain.model.Product;

public interface IProductPersistencePort {

    Product saveProduct(Product product);
    PaginatedResult<Product> findAllProducts(String sortOrder, int page, int size);
    PaginatedResult<Product> findProductsByName(String name, String sortOrder, int page, int size);

}
