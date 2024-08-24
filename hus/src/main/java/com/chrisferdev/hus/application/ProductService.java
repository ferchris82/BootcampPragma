package com.chrisferdev.hus.application;

import com.chrisferdev.hus.domain.model.Product;
import com.chrisferdev.hus.domain.port.IProductRepository;

public class ProductService {
    private final IProductRepository iProductRepository;

    public ProductService(IProductRepository iProductRepository) {
        this.iProductRepository = iProductRepository;
    }

    public Product saveProduct(Product product){
        return iProductRepository.saveProduct(product);
    }
}
