package com.chrisferdev.hus.infrastructure.adapter;

import com.chrisferdev.hus.domain.model.Product;
import com.chrisferdev.hus.domain.port.IProductRepository;
import com.chrisferdev.hus.infrastructure.mapper.ProductMapper;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class ProductCrudRepositoryImpl implements IProductRepository {
    private final IProductCrudRepository iProductCrudRepository;
    private final ProductMapper productMapper;

    public ProductCrudRepositoryImpl(IProductCrudRepository iProductCrudRepository, ProductMapper productMapper) {
        this.iProductCrudRepository = iProductCrudRepository;
        this.productMapper = productMapper;
    }

    @Override
    public Product saveProduct(Product product) {

        // Validar que el producto tenga entre 1 y 3 categorías
        Set<Long> categoryIds = product.getCategoryIds();
        Set<Long> uniqueCategoryIds = new HashSet<>(categoryIds);

        if (categoryIds.isEmpty() || categoryIds.size() > 3) {
            throw new IllegalArgumentException("El producto debe tener entre 1 y 3 categorías asociadas.");
        }
        // Validar que no haya categorías duplicadas
        if (categoryIds.size() != uniqueCategoryIds.size()) {
            throw new IllegalArgumentException("El producto no puede tener categorías duplicadas.");
        }

        // Devolver el producto guardado
        return productMapper.toProduct(iProductCrudRepository.save(productMapper.toProductEntity(product)));
    }
}
