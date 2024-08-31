package com.chrisferdev.pragmabackend.ports.driven.jpa.adapter;

import com.chrisferdev.pragmabackend.configuration.exception.ProductException;
import com.chrisferdev.pragmabackend.domain.model.PaginatedResult;
import com.chrisferdev.pragmabackend.domain.model.Product;
import com.chrisferdev.pragmabackend.domain.spi.output.IProductPersistencePort;
import com.chrisferdev.pragmabackend.ports.driven.jpa.entity.ProductEntity;
import com.chrisferdev.pragmabackend.ports.driven.jpa.mapper.ProductMapper;
import com.chrisferdev.pragmabackend.ports.driven.jpa.repository.IProductJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductJpaRepositoryImpl implements IProductPersistencePort {
    private final IProductJpaRepository iProductJpaRepository;
    private final ProductMapper productMapper;

    public ProductJpaRepositoryImpl(IProductJpaRepository iProductJpaRepository, ProductMapper productMapper) {
        this.iProductJpaRepository = iProductJpaRepository;
        this.productMapper = productMapper;
    }

    @Override
    public Product saveProduct(Product product) {
        List<Long> categoryIds = product.getCategoryIds();
        List<Long> brandIds = product.getBrandIds();
        if (categoryIds.isEmpty()) {
            throw new ProductException(ProductException.ErrorType.NO_CATEGORY, "El producto debe tener al menos una categoría asociada.");
        }

        if (categoryIds.size() > 3) {
            throw new ProductException(ProductException.ErrorType.TOO_MANY_CATEGORIES, "El producto no puede tener más de 3 categorías asociadas.");
        }

        Map<Long, Integer> frequencyMap = new HashMap<>();
        for (Long categoryId : categoryIds) {
            if (frequencyMap.containsKey(categoryId)) {
                throw new ProductException(ProductException.ErrorType.DUPLICATE_CATEGORIES, "El producto no puede tener categorías duplicadas.");
            }
            frequencyMap.put(categoryId, 1);
        }

        if (brandIds == null || brandIds.isEmpty()) {
            throw new ProductException(ProductException.ErrorType.NO_BRAND, "El producto debe tener al menos una marca asociada.");
        }

        return productMapper.toProduct(iProductJpaRepository.save(productMapper.toProductEntity(product)));
    }

    @Override
    public PaginatedResult<Product> findAllProducts(String sortOrder, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, "asc".equalsIgnoreCase(sortOrder) ? Sort.by("name").ascending() : Sort.by("name").descending());
        Page<ProductEntity> pageResult = iProductJpaRepository.findAll(pageable);
        List<Product> products = pageResult.getContent().stream()
                .map(productMapper::toProduct)
                .toList();
        return new PaginatedResult<>(products, pageResult.getNumber(), pageResult.getSize(), pageResult.getTotalElements());
    }

    @Override
    public PaginatedResult<Product> findProductsByName(String name, String sortOrder, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, "asc".equalsIgnoreCase(sortOrder) ? Sort.by("name").ascending() : Sort.by("name").descending());
        Page<ProductEntity> pageResult = iProductJpaRepository.findAll(pageable);
        List<Product> products = pageResult.getContent().stream()
                .map(productMapper::toProduct)
                .toList();
        return new PaginatedResult<>(products, pageResult.getNumber(), pageResult.getSize(), pageResult.getTotalElements());
    }


}
