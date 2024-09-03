package com.chrisferdev.hus.domain.spi.output;


import com.chrisferdev.hus.domain.model.PaginatedResult;
import com.chrisferdev.hus.domain.model.Product;
import com.chrisferdev.hus.infrastructure.driving.dto.response.ProductResponseDTO;

import java.util.List;

public interface IProductPersistencePort {

    Product saveProduct(Product product);
    PaginatedResult<ProductResponseDTO> findAllProducts(String sortOrder, int page, int size);
    PaginatedResult<Product> findProductsByName(String name, String sortOrder, int page, int size);
    PaginatedResult<Product> findProductsByBrand(Long brandId, String sortOrder, int page, int size);
    PaginatedResult<Product> findProductsByCategory(List<Long> categoryIds, String sortOrder, int page, int size, String sortBy);

}
