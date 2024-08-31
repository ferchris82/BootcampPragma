package com.chrisferdev.hus.hus5;

import com.chrisferdev.hus.domain.api.usecase.ProductServiceImpl;
import com.chrisferdev.hus.domain.model.Product;
import com.chrisferdev.hus.domain.spi.output.IProductPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
class HusFiveTest {

    IProductPersistencePort iProductPersistencePort;
    ProductServiceImpl productServiceImpl;

    @BeforeEach
    void setUp() {
        iProductPersistencePort = mock(IProductPersistencePort.class);
        productServiceImpl = new ProductServiceImpl(iProductPersistencePort);
    }

    @Test
    void saveProduct(){
        // Arrange
        Product product = new Product();
        product.setName("Producto de prueba");
        product.setDescription("Descripción del producto");
        product.setQuantity(10);
        product.setPrice(BigDecimal.valueOf(99.99));
        product.setCategoryIds(List.of(1L, 2L)); // Valid categories
        product.setBrandId(1); // Valid brand

        // Mock behavior Crea el objeto
        when(iProductPersistencePort.saveProduct(any(Product.class))).thenReturn(product);

        // Acti
        Product saveProduct = productServiceImpl.saveProduct(product);

        // Assert
        assertNotNull(saveProduct);
        assertEquals("Producto de prueba", saveProduct.getName());
        assertEquals("Descripción del producto", saveProduct.getDescription());
        assertEquals(10, saveProduct.getQuantity());
        assertEquals(BigDecimal.valueOf(99.99), saveProduct.getPrice());


        // Verify
        verify(iProductPersistencePort, times(1)).saveProduct(any(Product.class));
    }
}