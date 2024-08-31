package com.chrisferdev.hus.domain.api.usecase;


import com.chrisferdev.hus.domain.model.PaginatedResult;
import com.chrisferdev.hus.domain.model.Product;
import com.chrisferdev.hus.domain.spi.output.IProductPersistencePort;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl {
    private final IProductPersistencePort iProductPersistencePort;

    public ProductServiceImpl(IProductPersistencePort iProductPersistencePort) {
        this.iProductPersistencePort = iProductPersistencePort;
    }

    public Product saveProduct(Product product){
        return iProductPersistencePort.saveProduct(product);
    }

    public PaginatedResult<Product> findAllProducts(String sortOrder, int page, int size) {
        return iProductPersistencePort.findAllProducts(sortOrder, page, size);

    }

    public PaginatedResult<Product> findProductsByName(String name, String sortOrder, int page, int size) {
        return iProductPersistencePort.findProductsByName(name, sortOrder, page, size);
    }



}
