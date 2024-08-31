package com.chrisferdev.hus.hus3;

import com.chrisferdev.hus.domain.api.usecase.BrandServiceImpl;
import com.chrisferdev.hus.domain.model.Brand;
import com.chrisferdev.hus.domain.spi.output.IBrandPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class HusThreeTest {

    IBrandPersistencePort iBrandPersistencePort;
    BrandServiceImpl brandServiceImpl;

    @BeforeEach
    void setUp(){
        iBrandPersistencePort = mock(IBrandPersistencePort.class);
        brandServiceImpl = new BrandServiceImpl(iBrandPersistencePort);
    }

    @Test
    void saveBrand(){
        // Arrange
        Brand brand = new Brand();
        brand.setName("Marca prueba");
        brand.setDescription("Descripción Marca");

        // Mock behavior Crea el objeto
        when(iBrandPersistencePort.saveBrand(any(Brand.class))).thenReturn(brand);

        // Acti
        Brand saveBrand = brandServiceImpl.saveBrand(brand);

        // Assert
        assertNotNull(saveBrand);
        assertEquals("Marca prueba", saveBrand.getName());
        assertEquals("Descripción Marca", saveBrand.getDescription());

        // Verify
        verify(iBrandPersistencePort, times(1)).saveBrand(any(Brand.class));
    }
}