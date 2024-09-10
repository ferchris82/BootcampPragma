package com.chrisferdev.hus.usecase;

import com.chrisferdev.hus.configuration.exception.AddProductException;
import com.chrisferdev.hus.configuration.exception.FindProductByBrandException;
import com.chrisferdev.hus.configuration.exception.FindProductException;
import com.chrisferdev.hus.configuration.exception.exceptionhandler.ExceptionResponse;
import com.chrisferdev.hus.domain.api.usecase.ProductServiceImpl;
import com.chrisferdev.hus.domain.model.PaginatedResult;
import com.chrisferdev.hus.domain.model.Product;
import com.chrisferdev.hus.domain.spi.output.IProductPersistencePort;
import com.chrisferdev.hus.infrastructure.driven.jpa.mapper.ProductMapper;
import com.chrisferdev.hus.infrastructure.driven.jpa.repository.IBrandJpaRepository;
import com.chrisferdev.hus.infrastructure.driven.jpa.repository.IProductJpaRepository;
import com.chrisferdev.hus.infrastructure.driving.dto.response.ProductResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {
    private IProductPersistencePort iProductPersistencePort;
    private ProductMapper productMapper;
    private IProductJpaRepository iProductJpaRepository;
    private IBrandJpaRepository iBrandJpaRepository;
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        iProductPersistencePort = mock(IProductPersistencePort.class);
        productMapper = mock(ProductMapper.class);
        iProductJpaRepository = mock(IProductJpaRepository.class);
        iBrandJpaRepository = mock(IBrandJpaRepository.class);
        productService = new ProductServiceImpl(iProductPersistencePort, productMapper, iProductJpaRepository, iBrandJpaRepository);
    }

    @Test
    void saveProduct_validProduct_success() {

        Product product = new Product();
        product.setCategoryIds(List.of(1L, 2L));

        // Ejecutar el método a probar
        Product result = productService.saveProduct(product);

        // Verificar el resultado
        assertNotNull(result);
        verify(iProductJpaRepository).save(productMapper.toProductEntity(product));
    }

    @Test
    void saveProduct_invalidCategory_throwsAddProductException() {
        // Preparar datos de prueba
        Product product = new Product();
        product.setCategoryIds(List.of(1L, 2L, 3L, 4L));  // Exceso de categorías

        // Ejecutar el método a probar y verificar la excepción
        AddProductException thrown = assertThrows(
                AddProductException.class,
                () -> productService.saveProduct(product)
        );
        assertEquals(ExceptionResponse.ERROR_CATEGORY.getMessage(), thrown.getMessage());
    }

    @Test
    void findAllProducts_validInput_success() {
        // Preparar datos de prueba
        PaginatedResult<ProductResponseDTO> paginatedResult = new PaginatedResult<>();
        paginatedResult.setItems(List.of(new ProductResponseDTO(), new ProductResponseDTO()));
        paginatedResult.setTotal(2);

        when(iProductPersistencePort.findAllProducts("asc", 0, 10)).thenReturn(paginatedResult);

        // Ejecutar el método a probar
        PaginatedResult<ProductResponseDTO> result = productService.findAllProducts("asc", 0, 10);

        // Verificar el resultado
        assertNotNull(result);
        assertEquals(2, result.getTotal());
        assertEquals(2, result.getItems().size());
        verify(iProductPersistencePort).findAllProducts("asc", 0, 10);
    }

    @Test
    void findProductsByName_productNotFound_throwsFindProductException() {
        // Preparar datos de prueba
        String name = "Nonexistent Product";

        when(iProductJpaRepository.existsByName(name)).thenReturn(false);

        // Ejecutar el método a probar y verificar la excepción
        FindProductException thrown = assertThrows(
                FindProductException.class,
                () -> productService.findProductsByName(name, "asc", 0, 10)
        );
        assertEquals(ExceptionResponse.PRODUCT_NOT_FOUND.getMessage(), thrown.getMessage());
    }

    @Test
    void findProductsByBrand_brandNotFound_throwsFindProductByBrandException() {
        // Preparar datos de prueba
        Long brandId = 1L;

        when(iBrandJpaRepository.existsById(brandId)).thenReturn(false);

        // Ejecutar el método a probar y verificar la excepción
        FindProductByBrandException thrown = assertThrows(
                FindProductByBrandException.class,
                () -> productService.findProductsByBrand(brandId, "asc", 0, 10)
        );
        assertEquals(ExceptionResponse.PRODUCT_BRAND_NOT_FOUND.getMessage(), thrown.getMessage());
    }

    @Test
    void findProductsByCategory_validInput_success() {
        // Preparar datos de prueba
        List<Long> categoryIds = List.of(1L, 2L);
        PaginatedResult<Product> paginatedResult = new PaginatedResult<>();
        paginatedResult.setItems(List.of(new Product(), new Product()));
        paginatedResult.setTotal(2);

        when(iProductPersistencePort.findProductsByCategory(categoryIds, "asc", 0, 10, "name")).thenReturn(paginatedResult);

        // Ejecutar el método a probar
        PaginatedResult<Product> result = productService.findProductsByCategory(categoryIds, "asc", 0, 10, "name");

        // Verificar el resultado
        assertNotNull(result);
        assertEquals(2, result.getTotal());
        assertEquals(2, result.getItems().size());
        verify(iProductPersistencePort).findProductsByCategory(categoryIds, "asc", 0, 10, "name");
    }
}
