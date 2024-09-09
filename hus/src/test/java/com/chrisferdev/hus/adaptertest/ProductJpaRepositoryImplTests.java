package com.chrisferdev.hus.adaptertest;


import com.chrisferdev.hus.domain.model.PaginatedResult;
import com.chrisferdev.hus.domain.model.Product;
import com.chrisferdev.hus.infrastructure.driven.jpa.adapter.ProductJpaRepositoryImpl;
import com.chrisferdev.hus.infrastructure.driven.jpa.entity.ProductEntity;
import com.chrisferdev.hus.infrastructure.driven.jpa.mapper.ProductMapper;
import com.chrisferdev.hus.infrastructure.driven.jpa.repository.IBrandJpaRepository;
import com.chrisferdev.hus.infrastructure.driven.jpa.repository.IProductJpaRepository;
import com.chrisferdev.hus.infrastructure.driving.dto.response.ProductResponseDTO;
import com.chrisferdev.hus.infrastructure.driving.mapper.ProductResponseMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductJpaRepositoryImplTest {

    private IProductJpaRepository iProductJpaRepository;
    private IBrandJpaRepository iBrandJpaRepository;
    private ProductMapper productMapper;
    private ProductResponseMapper productResponseMapper;
    private ProductJpaRepositoryImpl productJpaRepositoryImpl;

    @BeforeEach
    void setUp() {
        iProductJpaRepository = mock(IProductJpaRepository.class);
        iBrandJpaRepository = mock(IBrandJpaRepository.class);
        productMapper = mock(ProductMapper.class);
        productResponseMapper = mock(ProductResponseMapper.class);
        productJpaRepositoryImpl = new ProductJpaRepositoryImpl(iProductJpaRepository, productMapper, productResponseMapper);
    }

    @Test
    void saveProduct_successful() {
        Product product = new Product();
        product.setCategoryIds(List.of(1L, 2L));

        ProductEntity productEntity = new ProductEntity();
        when(productMapper.toProductEntity(any(Product.class))).thenReturn(productEntity);
        when(iProductJpaRepository.save(any(ProductEntity.class))).thenReturn(productEntity);
        when(productMapper.toProduct(any(ProductEntity.class))).thenReturn(product);

        Product result = productJpaRepositoryImpl.saveProduct(product);

        assertNotNull(result);
        verify(iProductJpaRepository).save(productEntity);
    }

    @Test
    void findAllProducts_successful() {
        Pageable pageable = PageRequest.of(0, 1, Sort.by("name").ascending());
        ProductEntity productEntity = new ProductEntity();
        ProductResponseDTO productResponseDTO = new ProductResponseDTO(); // Cambia esto según tu DTO real
        Page<ProductEntity> productPage = new PageImpl<>(List.of(productEntity));

        when(iProductJpaRepository.findAll(pageable)).thenReturn(productPage);
        when(productResponseMapper.toProductResponseDTO(any(ProductEntity.class))).thenReturn(productResponseDTO);

        PaginatedResult<ProductResponseDTO> result = productJpaRepositoryImpl.findAllProducts("asc", 0, 1);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(1, result.getItems().size());
    }

    @Test
    void findProductsByName_successful() {
        Pageable pageable = PageRequest.of(0, 1, Sort.by("name").ascending());
        ProductEntity productEntity = new ProductEntity();
        Product product = new Product();
        Page<ProductEntity> productPage = new PageImpl<>(List.of(productEntity));

        when(iProductJpaRepository.existsByName("Product 1")).thenReturn(true);
        when(iProductJpaRepository.findProductByName("Product 1", pageable)).thenReturn(productPage);
        when(productMapper.toProduct(any(ProductEntity.class))).thenReturn(product);

        PaginatedResult<Product> result = productJpaRepositoryImpl.findProductsByName("Product 1", "asc", 0, 1);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(1, result.getItems().size());
    }


    @Test
    void findProductsByBrand_successful() {
        Pageable pageable = PageRequest.of(0, 1, Sort.by("name").ascending());
        ProductEntity productEntity = new ProductEntity();
        Product product = new Product();
        Page<ProductEntity> productPage = new PageImpl<>(List.of(productEntity));

        when(iBrandJpaRepository.existsById(1L)).thenReturn(true);
        when(iProductJpaRepository.findByBrandEntityId(1L, pageable)).thenReturn(productPage);
        when(productMapper.toProduct(any(ProductEntity.class))).thenReturn(product);

        PaginatedResult<Product> result = productJpaRepositoryImpl.findProductsByBrand(1L, "asc", 0, 1);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(1, result.getItems().size());
    }


    @Test
    void findProductsByCategory_successful() {
        Pageable pageable = PageRequest.of(0, 1, Sort.by("name").ascending());
        ProductEntity productEntity = new ProductEntity();
        Product product = new Product();
        Page<ProductEntity> productPage = new PageImpl<>(List.of(productEntity));

        when(iProductJpaRepository.findByCategoryIds(anyList(), eq(pageable))).thenReturn(productPage);
        when(productMapper.toProduct(any(ProductEntity.class))).thenReturn(product);

        PaginatedResult<Product> result = productJpaRepositoryImpl.findProductsByCategory(List.of(1L, 2L), "asc", 0, 1, "name");

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(1, result.getItems().size());
    }

}