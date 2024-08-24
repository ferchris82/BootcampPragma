package com.chrisferdev.hus.hus5;

import com.chrisferdev.hus.application.ProductService;
import com.chrisferdev.hus.domain.model.Product;
import com.chrisferdev.hus.domain.port.IProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class TestingH5 {
    IProductRepository iProductRepository;
    ProductService productService;

    @BeforeEach
    void setUp(){
        iProductRepository = mock(IProductRepository.class);
        productService = new ProductService(iProductRepository);
    }

    @Test
    void saveProduct(){
        // Arrange
        Product product = new Product();
        product.setName("Producto Prueba");
        product.setCategoryIds(Set.of(1L, 2L, 3L));

        Product savedProduct = new Product();
        savedProduct.setName("Producto Prueba");

        when(iProductRepository.saveProduct(product)).thenReturn(savedProduct);

        Product result = productService.saveProduct(product);

        // Assert ** Verifica el resultado
        assertNotNull(result);
        assertEquals("Producto Prueba", result.getName());
        verify(iProductRepository, times(1)).saveProduct(product);
    }

    @Test
    void saveProductWithCategory() {
        // Arrange
        Product product = new Product();
        product.setName("Producto Prueba");
        product.setCategoryIds(Set.of(1L, 2L, 3L));

        // Act
        productService.saveProduct(product);

        // Assert
        verify(iProductRepository, times(1)).saveProduct(product);  // Verifica que el método saveProduct fue llamado exactamente una vez
    }

    @Test
    void saveProductWithoutCategoryThrowsException() {
        // Arrange
        Product product = new Product();
        product.setName("Producto Prueba");
        product.setCategoryIds(Collections.emptySet());  // No se agrega ninguna categoría

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            productService.saveProduct(product);
        });

        // Verifica que el método saveProduct del repositorio no fue llamado
        verify(iProductRepository, never()).saveProduct(any(Product.class));
    }
}
