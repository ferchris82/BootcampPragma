package com.chrisferdev.hus.adaptertest;

import com.chrisferdev.hus.configuration.exception.BrandInvalidException;
import com.chrisferdev.hus.configuration.exception.exceptionhandler.ExceptionResponse;
import com.chrisferdev.hus.domain.model.Brand;
import com.chrisferdev.hus.domain.model.PaginatedResult;
import com.chrisferdev.hus.ports.driven.jpa.adapter.BrandJpaRepositoryImpl;
import com.chrisferdev.hus.ports.driven.jpa.entity.BrandEntity;
import com.chrisferdev.hus.ports.driven.jpa.mapper.BrandMapper;
import com.chrisferdev.hus.ports.driven.jpa.repository.IBrandJpaRepository;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import org.springframework.data.domain.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BrandJpaRepositoryImplTest {

    private IBrandJpaRepository iBrandJpaRepository;
    private BrandMapper brandMapper;
    private BrandJpaRepositoryImpl brandJpaRepositoryImpl;

    @BeforeEach
    void setUp() {
        iBrandJpaRepository = mock(IBrandJpaRepository.class);
        brandMapper = mock(BrandMapper.class);
        brandJpaRepositoryImpl = new BrandJpaRepositoryImpl(iBrandJpaRepository, brandMapper);
    }

    @Test
    void saveBrand_successful() {
        Brand brand = new Brand();
        brand.setName("BrandName");
        brand.setDescription("BrandDescription");

        BrandEntity brandEntity = new BrandEntity();
        when(brandMapper.toBrandEntity(any(Brand.class))).thenReturn(brandEntity);
        when(iBrandJpaRepository.save(any(BrandEntity.class))).thenReturn(brandEntity);
        when(brandMapper.toBrand(any(BrandEntity.class))).thenReturn(brand);

        Brand result = brandJpaRepositoryImpl.saveBrand(brand);

        assertNotNull(result);
        verify(iBrandJpaRepository).save(brandEntity);
    }

    @Test
    void saveBrand_errorBrandAlreadyExists() {
        Brand brand = new Brand();
        brand.setName("BrandName");

        when(iBrandJpaRepository.existsByName("BrandName")).thenReturn(true);

        BrandInvalidException exception = assertThrows(BrandInvalidException.class, () -> brandJpaRepositoryImpl.saveBrand(brand));

        assertEquals(ExceptionResponse.BRAND_ALREADY_EXISTS.getMessage(), exception.getMessage());
        verify(iBrandJpaRepository, never()).save(any());
    }

    @Test
    void saveBrand_errorInvalidBrand_emptyName() {
        Brand brand = new Brand();
        brand.setName("");
        brand.setDescription("BrandDescription");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> brandJpaRepositoryImpl.saveBrand(brand));

        assertEquals(ExceptionResponse.INVALID_BRAND.getMessage(), exception.getMessage());
        verify(iBrandJpaRepository, never()).save(any());
    }

    @Test
    void saveBrand_errorInvalidBrand_emptyDescription() {
        Brand brand = new Brand();
        brand.setName("BrandName");
        brand.setDescription("");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> brandJpaRepositoryImpl.saveBrand(brand));

        assertEquals(ExceptionResponse.INVALID_BRAND.getMessage(), exception.getMessage());
        verify(iBrandJpaRepository, never()).save(any());
    }

    @Test
    void existsByName_successful() {
        when(iBrandJpaRepository.existsByName("BrandName")).thenReturn(true);

        boolean result = brandJpaRepositoryImpl.existsByName("BrandName");

        assertTrue(result);
        verify(iBrandJpaRepository).existsByName("BrandName");
    }

    @Test
    void findAllBrands_successful_ascending() {
        Pageable pageable = PageRequest.of(0, 1, Sort.by("name").ascending());
        BrandEntity brandEntity = new BrandEntity();
        Brand brand = new Brand();
        Page<BrandEntity> brandPage = new PageImpl<>(List.of(brandEntity));

        when(iBrandJpaRepository.findAllByOrderByNameAsc(pageable)).thenReturn(brandPage);
        when(brandMapper.toBrand(any(BrandEntity.class))).thenReturn(brand);

        PaginatedResult<Brand> result = brandJpaRepositoryImpl.findAllBrands("asc", 0, 1);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(1, result.getItems().size());
    }

    @Test
    void findAllBrands_successful_descending() {
        Pageable pageable = PageRequest.of(0, 1, Sort.by("name").descending());
        BrandEntity brandEntity = new BrandEntity();
        Brand brand = new Brand();
        Page<BrandEntity> brandPage = new PageImpl<>(List.of(brandEntity));

        when(iBrandJpaRepository.findAllByOrderByNameDesc(pageable)).thenReturn(brandPage);
        when(brandMapper.toBrand(any(BrandEntity.class))).thenReturn(brand);

        PaginatedResult<Brand> result = brandJpaRepositoryImpl.findAllBrands("desc", 0, 1);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(1, result.getItems().size());
    }
}