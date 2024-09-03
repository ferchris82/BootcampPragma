package com.chrisferdev.hus.domain.api.usecase;

import com.chrisferdev.hus.configuration.exception.FindProductByBrandException;
import com.chrisferdev.hus.configuration.exception.FindProductException;
import com.chrisferdev.hus.configuration.exception.ProductSaveException;
import com.chrisferdev.hus.domain.model.PaginatedResult;
import com.chrisferdev.hus.domain.model.Product;
import com.chrisferdev.hus.domain.spi.output.IProductPersistencePort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl {

    private final IProductPersistencePort iProductPersistencePort;

    public ProductServiceImpl(IProductPersistencePort iProductPersistencePort) {
        this.iProductPersistencePort = iProductPersistencePort;
    }

    public Product saveProduct(Product product) {
        try {
            return iProductPersistencePort.saveProduct(product);
        } catch (Exception e) {
            throw new ProductSaveException(e.getMessage());
        }
    }

    public PaginatedResult<Product> findAllProducts(String sortOrder, int page, int size) {
        try {
            return iProductPersistencePort.findAllProducts(sortOrder, page, size);
        } catch (Exception e) {
            throw new FindProductException(e.getMessage());
        }
    }

    public PaginatedResult<Product> findProductsByName(String name, String sortOrder, int page, int size) {
        try {
            return iProductPersistencePort.findProductsByName(name, sortOrder, page, size);
        } catch (Exception e) {
            throw new FindProductException(e.getMessage());
        }
    }

    public PaginatedResult<Product> findProductsByBrand(Long brandId, String sortOrder, int page, int size) {
        try {
            return iProductPersistencePort.findProductsByBrand(brandId, sortOrder, page, size);
        } catch (Exception e) {
            throw new FindProductByBrandException(e.getMessage());
        }
    }

    public PaginatedResult<Product> findProductsByCategory(List<Long> categoryIds, String sortOrder, int page, int size, String sortBy) {
        try {
            return iProductPersistencePort.findProductsByCategory(categoryIds, sortOrder, page, size, sortBy);
        } catch (Exception e) {
            throw new FindProductException(e.getMessage());
        }
    }
}
