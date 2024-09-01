package com.chrisferdev.hus.hus6;

import com.chrisferdev.hus.domain.api.usecase.ProductServiceImpl;
import com.chrisferdev.hus.domain.model.PaginatedResult;
import com.chrisferdev.hus.domain.model.Product;
import com.chrisferdev.hus.domain.spi.output.IProductPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class HusSixTest {

     IProductPersistencePort iProductPersistencePort;
     ProductServiceImpl productServiceImpl;

    @BeforeEach
    void setUp() {
        iProductPersistencePort = mock(IProductPersistencePort.class);
        productServiceImpl = new ProductServiceImpl(iProductPersistencePort);
    }

    @Test
    void findProductsByBrand() {
        // Arrange
        Product product = new Product();
        product.setName("Product 1");
        List<Product> products = List.of(product);
        PaginatedResult<Product> paginatedResult = new PaginatedResult<>(
                products,  // items
                0,           // number (página actual)
                1,           // size (tamaño de la página)
                1            // totalElements (total de elementos en la base de datos)
        );

        // Configura el mock para que devuelva el resultado paginado
        when(iProductPersistencePort.findProductsByBrand(1L, "asc", 0, 1)).thenReturn(paginatedResult);

        // Act
        PaginatedResult<Product> result = productServiceImpl.findProductsByBrand(1L, "asc", 0, 1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getTotalElements()); // Verifica el número total de elementos
        assertEquals(1, result.getItems().size()); // Verifica el tamaño de los items en la página
        assertEquals("Product 1", result.getItems().get(0).getName()); // Verifica el nombre del producto
        assertEquals(0, result.getNumber()); // Verifica el número de la página actual
        assertEquals(1, result.getSize()); // Verifica el tamaño de la página
        verify(iProductPersistencePort, times(1)).findProductsByBrand(1L, "asc", 0, 1); // Verifica que el método fue llamado una vez
    }

    @Test
    void findProductsByCategory() {
        // Arrange
        Product product = new Product();
        product.setName("Product 1");
        List<Product> products = List.of(product);
        PaginatedResult<Product> paginatedResult = new PaginatedResult<>(
                products,  // items
                0,           // number (página actual)
                1,           // size (tamaño de la página)
                1            // totalElements (total de elementos en la base de datos)
        );

        // Configura el mock para que devuelva el resultado paginado
        when(iProductPersistencePort.findProductsByCategory(List.of(1L), "asc", 0, 1, "name")).thenReturn(paginatedResult);

        // Act
        PaginatedResult<Product> result = productServiceImpl.findProductsByCategory(List.of(1L), "asc", 0, 1, "name");

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getTotalElements()); // Verifica el número total de elementos
        assertEquals(1, result.getItems().size()); // Verifica el tamaño de los items en la página
        assertEquals("Product 1", result.getItems().get(0).getName()); // Verifica el nombre del producto
        assertEquals(0, result.getNumber()); // Verifica el número de la página actual
        assertEquals(1, result.getSize()); // Verifica el tamaño de la página
        verify(iProductPersistencePort, times(1)).findProductsByCategory(List.of(1L), "asc", 0, 1, "name"); // Verifica que el método fue llamado una vez
    }




}
