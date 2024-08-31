package com.chrisferdev.pragmabackend.ports.driven.jpa.adapter;

import com.chrisferdev.pragmabackend.configuration.exception.BrandAlreadyExistsException;
import com.chrisferdev.pragmabackend.configuration.exception.exceptionhandler.ExceptionResponse;
import com.chrisferdev.pragmabackend.domain.model.Brand;
import com.chrisferdev.pragmabackend.domain.model.PaginatedResult;
import com.chrisferdev.pragmabackend.domain.spi.output.IBrandPersistencePort;
import com.chrisferdev.pragmabackend.ports.driven.jpa.entity.BrandEntity;
import com.chrisferdev.pragmabackend.ports.driven.jpa.mapper.BrandMapper;
import com.chrisferdev.pragmabackend.ports.driven.jpa.repository.IBrandJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BrandJpaRepositoryImpl implements IBrandPersistencePort {

    private final IBrandJpaRepository iBrandJpaRepository;
    private final BrandMapper brandMapper;

    public BrandJpaRepositoryImpl(IBrandJpaRepository iBrandJpaRepository, BrandMapper brandMapper) {
        this.iBrandJpaRepository = iBrandJpaRepository;
        this.brandMapper = brandMapper;
    }

    @Override
    public Brand saveBrand(Brand brand) {
        if(existsByName(brand.getName())){
            throw new BrandAlreadyExistsException(ExceptionResponse.BRAND_ALREADY_EXISTS.getMessage());
        }
        if(brand.getDescription().trim().isEmpty() || brand.getName().trim().isEmpty()){
            throw new IllegalArgumentException(ExceptionResponse.INVALID_BRAND.getMessage());
        }
        return brandMapper.toBrand(iBrandJpaRepository.save(brandMapper.toBrandEntity(brand)));
    }

    @Override
    public boolean existsByName(String name) {
        return iBrandJpaRepository.existsByName(name);
    }

    @Override
    public PaginatedResult<Brand> findAllBrands(String sortOrder, int page, int size) {
        Pageable pageable = PageRequest.of(page, size,
                "asc".equalsIgnoreCase(sortOrder) ? Sort.by("name").ascending() : Sort.by("name").descending());
        Page<BrandEntity> pageResult =
                "asc".equalsIgnoreCase(sortOrder) ?
                        iBrandJpaRepository.findAllByOrderByNameAsc(pageable) :
                        iBrandJpaRepository.findAllByOrderByNameDesc(pageable);
        List<Brand> brands = pageResult.getContent().stream()
                .map(brandMapper::toBrand)
                .toList();
        return new PaginatedResult<>(brands, pageResult.getNumber(), pageResult.getSize(), pageResult.getTotalElements());
    }
}
