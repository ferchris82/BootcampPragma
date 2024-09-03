package com.chrisferdev.hus.domain.api.usecase;

import com.chrisferdev.hus.configuration.exception.AddProductException;
import com.chrisferdev.hus.configuration.exception.FindProductByBrandException;
import com.chrisferdev.hus.configuration.exception.FindProductException;
import com.chrisferdev.hus.configuration.exception.exceptionhandler.ExceptionResponse;
import com.chrisferdev.hus.domain.model.PaginatedResult;
import com.chrisferdev.hus.domain.model.Product;
import com.chrisferdev.hus.domain.spi.output.IProductPersistencePort;
import com.chrisferdev.hus.infrastructure.driven.jpa.mapper.ProductMapper;
import com.chrisferdev.hus.infrastructure.driven.jpa.repository.IBrandJpaRepository;
import com.chrisferdev.hus.infrastructure.driven.jpa.repository.IProductJpaRepository;

import com.chrisferdev.hus.infrastructure.driving.dto.response.ProductResponseDTO;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProductServiceImpl {

    private final IProductPersistencePort iProductPersistencePort;
    private final ProductMapper productMapper;
    private final IProductJpaRepository iProductJpaRepository;
    private final IBrandJpaRepository iBrandJpaRepository;

    public ProductServiceImpl(IProductPersistencePort iProductPersistencePort,
                              ProductMapper productMapper, IProductJpaRepository iProductJpaRepository,
                              IBrandJpaRepository iBrandJpaRepository) {
        this.iProductPersistencePort = iProductPersistencePort;
        this.productMapper = productMapper;
        this.iProductJpaRepository = iProductJpaRepository;
        this.iBrandJpaRepository = iBrandJpaRepository;
    }

    public Product saveProduct(Product product) {
        List<Long> categoryIds = product.getCategoryIds();
        Set<Long> uniqueCategoryIds = new HashSet<>(categoryIds);
        if (categoryIds.isEmpty() || categoryIds.size() > 3 || categoryIds.size() != uniqueCategoryIds.size()) {
            throw new AddProductException(ExceptionResponse.ERROR_CATEGORY.getMessage());
        }
        return productMapper.toProduct(
                iProductJpaRepository.save(productMapper.toProductEntity(product)));
    }

    public PaginatedResult<ProductResponseDTO> findAllProducts(String sortOrder, int page, int size) {
        try {
            return iProductPersistencePort.findAllProducts(sortOrder, page, size);
        } catch (Exception e) {
            throw new FindProductException(e.getMessage());
        }
    }

    public PaginatedResult<Product> findProductsByName(String name, String sortOrder, int page, int size) {
        if (!iProductJpaRepository.existsByName(name)) {
            throw new FindProductException(ExceptionResponse.PRODUCT_NOT_FOUND.getMessage());
        }
        return iProductPersistencePort.findProductsByName(name, sortOrder, page, size);

    }

    public PaginatedResult<Product> findProductsByBrand(Long brandId, String sortOrder, int page, int size) {
        if (!iBrandJpaRepository.existsById(brandId)) {
            throw new FindProductByBrandException(ExceptionResponse.PRODUCTBRAND_NOT_FOUND.getMessage());
        }
        return iProductPersistencePort.findProductsByBrand(brandId, sortOrder, page, size);

    }

    public PaginatedResult<Product> findProductsByCategory(List<Long> categoryIds, String sortOrder, int page, int size, String sortBy) {
        try {
            return iProductPersistencePort.findProductsByCategory(categoryIds, sortOrder, page, size, sortBy);
        } catch (Exception e) {
            throw new FindProductException(e.getMessage());
        }
    }
}
