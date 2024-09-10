package com.chrisferdev.hus.infrastructure.driven.jpa.adapter;

import com.chrisferdev.hus.domain.model.PaginatedResult;
import com.chrisferdev.hus.domain.model.Product;
import com.chrisferdev.hus.domain.spi.output.IProductPersistencePort;
import com.chrisferdev.hus.infrastructure.driven.jpa.entity.ProductEntity;
import com.chrisferdev.hus.infrastructure.driven.jpa.mapper.ProductMapper;
import com.chrisferdev.hus.infrastructure.driven.jpa.repository.IProductJpaRepository;
import com.chrisferdev.hus.infrastructure.driving.dto.response.ProductResponseDTO;
import com.chrisferdev.hus.infrastructure.driving.mapper.ProductResponseMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public class ProductJpaRepositoryImpl implements IProductPersistencePort {
    private final IProductJpaRepository iProductJpaRepository;
    private final ProductMapper productMapper;
    private final ProductResponseMapper productResponseMapper;

    public ProductJpaRepositoryImpl(IProductJpaRepository iProductJpaRepository, ProductMapper productMapper, ProductResponseMapper productResponseMapper) {
        this.iProductJpaRepository = iProductJpaRepository;
        this.productMapper = productMapper;
        this.productResponseMapper = productResponseMapper;
    }

    private Pageable createPageable(int page, int size, String sortOrder) {
        return PageRequest.of(
                page, size, "asc".equalsIgnoreCase(sortOrder) ? Sort.by("name").ascending() : Sort.by("name").descending());
    }

    @Override
    public Product saveProduct(Product product) {
        return productMapper.toProduct(
                iProductJpaRepository.save(productMapper.toProductEntity(product)));
    }

    @Override
    public PaginatedResult<ProductResponseDTO> findAllProducts(String sortOrder, int page, int size) {
        Pageable pageable = createPageable(page, size, sortOrder);
        Page<ProductEntity> pageResult = iProductJpaRepository.findAll(pageable);
        List<ProductResponseDTO> products = pageResult.getContent().stream()
                .map(productResponseMapper::toProductResponseDTO)
                .toList();
        return new PaginatedResult<>(
                products, pageResult.getNumber(), pageResult.getSize(), pageResult.getTotalElements());
    }

    @Override
    public PaginatedResult<Product> findProductsByName(String name, String sortOrder, int page, int size) {
        Pageable pageable = createPageable(page, size, sortOrder);
        Page<ProductEntity> pageResult = iProductJpaRepository.findProductByName(name, pageable);
        List<Product> products = pageResult.getContent().stream()
                .map(productMapper::toProduct)
                .toList();
        return new PaginatedResult<>(
                products, pageResult.getNumber(), pageResult.getSize(), pageResult.getTotalElements());
    }

    @Override
    public PaginatedResult<Product> findProductsByBrand(Long brandId, String sortOrder, int page, int size) {
        Pageable pageable = createPageable(page, size, sortOrder);
        Page<ProductEntity> pageResult = iProductJpaRepository.findByBrandEntityId(brandId, pageable);
        List<Product> products = pageResult.getContent().stream()
                .map(productMapper::toProduct)
                .toList();
        return new PaginatedResult<>(
                products, pageResult.getNumber(), pageResult.getSize(), pageResult.getTotalElements());
    }

    @Override
    public PaginatedResult<Product> findProductsByCategory(List<Long> categoryIds, String sortOrder, int page, int size, String sortBy) {
        Pageable pageable = createPageable(page, size, sortOrder);
        Page<ProductEntity> pageResult = iProductJpaRepository.findByCategoryIds(categoryIds, pageable);
        List<Product> products = pageResult.getContent().stream()
                .map(productMapper::toProduct)
                .toList();

        return new PaginatedResult<>(
                products, pageResult.getNumber(), pageResult.getSize(), pageResult.getTotalElements());

    }


}
