package com.chrisferdev.hus.ports.driven.jpa.adapter;

import com.chrisferdev.hus.configuration.exception.AddProductException;
import com.chrisferdev.hus.configuration.exception.FindProductByBrandException;
import com.chrisferdev.hus.configuration.exception.FindProductException;
import com.chrisferdev.hus.configuration.exception.exceptionhandler.ExceptionResponse;
import com.chrisferdev.hus.domain.model.PaginatedResult;
import com.chrisferdev.hus.domain.model.Product;
import com.chrisferdev.hus.domain.spi.output.IProductPersistencePort;
import com.chrisferdev.hus.ports.driven.jpa.entity.ProductEntity;
import com.chrisferdev.hus.ports.driven.jpa.mapper.ProductMapper;
import com.chrisferdev.hus.ports.driven.jpa.repository.IBrandJpaRepository;
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
    private final IBrandJpaRepository iBrandJpaRepository;
    private final ProductMapper productMapper;

    public ProductJpaRepositoryImpl(IProductJpaRepository iProductJpaRepository, IBrandJpaRepository iBrandJpaRepository, ProductMapper productMapper) {
        this.iProductJpaRepository = iProductJpaRepository;
        this.iBrandJpaRepository = iBrandJpaRepository;
        this.productMapper = productMapper;
    }

    private Pageable createPageable(int page, int size, String sortOrder) {
        return PageRequest.of(
                page, size, "asc".equalsIgnoreCase(sortOrder) ? Sort.by("name").ascending() : Sort.by("name").descending());
    }

    @Override
    public Product saveProduct(Product product) {
        List<Long> categoryIds = product.getCategoryIds();
        Set<Long> uniqueCategoryIds = new HashSet<>(categoryIds);

        if (categoryIds.isEmpty() || categoryIds.size() > 3 || categoryIds.size() != uniqueCategoryIds.size()) {
            throw new AddProductException(ExceptionResponse.ERROR_CATEGORY.getMessage());
        }

        return productMapper.toProduct(
                iProductJpaRepository.save(productMapper.toProductEntity(product)));
    }

    @Override
    public PaginatedResult<Product> findAllProducts(String sortOrder, int page, int size) {
        Pageable pageable = createPageable(page, size, sortOrder);
        Page<ProductEntity> pageResult = iProductJpaRepository.findAll(pageable);
        List<Product> products = pageResult.getContent().stream()
                .map(productMapper::toProduct)
                .toList();
        return new PaginatedResult<>(
                products, pageResult.getNumber(), pageResult.getSize(), pageResult.getTotalElements());
    }

    @Override
    public PaginatedResult<Product> findProductsByName(String name, String sortOrder, int page, int size) {
        if (!iProductJpaRepository.existsByName(name)) {
            throw new FindProductException(ExceptionResponse.PRODUCT_NOT_FOUND.getMessage());
        }
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
        if (!iBrandJpaRepository.existsById(brandId)) {
            throw new FindProductByBrandException(ExceptionResponse.PRODUCTBRAND_NOT_FOUND.getMessage());
        }
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
