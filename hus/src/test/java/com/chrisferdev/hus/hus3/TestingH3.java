package com.chrisferdev.hus.hus3;

import com.chrisferdev.hus.application.BrandService;
import com.chrisferdev.hus.domain.model.Brand;
import com.chrisferdev.hus.domain.port.IBrandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class TestingH3 {

    IBrandRepository iBrandRepository;
    BrandService brandService;

    @BeforeEach
    void setUp(){
        iBrandRepository = mock(IBrandRepository.class);
        brandService = new BrandService(iBrandRepository);
    }

    @Test
    void saveBrand(){
        //Arrange
        Brand brand = new Brand();
        brand.setName("Test Brand");
        Brand saveBrand = new Brand();
        saveBrand.setName("Test Brand");

        when(iBrandRepository.saveBrand(brand)).thenReturn(saveBrand);

        Brand result = brandService.saveBrand(brand);

        // Assert ** Verifica el resultado
        assertNotNull(result);
        assertEquals("Test Brand", result.getName());
        verify(iBrandRepository, times(1)).saveBrand(brand);
    }

    @Test
    void existsByName(){
        // Arrange
        String brandName = "Test Brand";
        boolean exists = true;

        // Mock behavior
        when(iBrandRepository.existsByName(brandName)).thenReturn(exists);

        // Act
        boolean result = iBrandRepository.existsByName(brandName);

        // Assert
        assertTrue(result);
        verify(iBrandRepository, times(1)).existsByName(brandName);
    }

}
