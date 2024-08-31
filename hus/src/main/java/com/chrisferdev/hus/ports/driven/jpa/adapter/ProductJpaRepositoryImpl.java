package com.chrisferdev.hus.ports.driven.jpa.adapter;

import com.chrisferdev.hus.domain.model.PaginatedResult;
import com.chrisferdev.hus.domain.model.Product;
import com.chrisferdev.hus.domain.spi.output.IProductPersistencePort;
import com.chrisferdev.hus.ports.driven.jpa.entity.ProductEntity;
import com.chrisferdev.hus.ports.driven.jpa.mapper.ProductMapper;
import com.chrisferdev.hus.ports.driven.jpa.repository.IProductJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


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
        Set<Long> uniqueCategoryIds = new HashSet<>(categoryIds);

        if (categoryIds.isEmpty() || categoryIds.size() > 3 || categoryIds.size() != uniqueCategoryIds.size()) {
            throw new IllegalArgumentException();
        }

        return productMapper.toProduct(
                iProductJpaRepository.save(productMapper.toProductEntity(product)));
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
