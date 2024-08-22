package com.chrisferdev.hus.hus4;

import com.chrisferdev.hus.application.BrandService;
import com.chrisferdev.hus.domain.model.Brand;
import com.chrisferdev.hus.domain.port.IBrandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
class TestingH4 {
    IBrandRepository iBrandRepository;
    BrandService brandService;

    @BeforeEach
    void setUp(){
        iBrandRepository = mock(IBrandRepository.class);
        brandService = new BrandService(iBrandRepository);
    }

    @Test
    void findAllBrands(){
        Brand brand = new Brand();
        brand.setName("Marca 1");
        List<Brand> brands = List.of(brand);
        Page<Brand> brandPage = new PageImpl<>(brands);
        Pageable pageable = Pageable.ofSize(3);

        when(iBrandRepository.findAllBrands("asc", pageable)).thenReturn(brandPage);

        Page<Brand> result = brandService.findAllBrands("asc", pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals("Marca 1", result.getContent().get(0).getName());
        verify(iBrandRepository, times(1)).findAllBrands("asc", pageable);
    }
}
