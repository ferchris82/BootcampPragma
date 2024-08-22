package com.chrisferdev.hus.infrastructure.adapter;

import com.chrisferdev.hus.domain.model.Brand;
import com.chrisferdev.hus.domain.port.IBrandRepository;
import com.chrisferdev.hus.infrastructure.entity.BrandEntity;
import com.chrisferdev.hus.infrastructure.mapper.BrandMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class BrandCrudRepositoryImpl implements IBrandRepository {
    private final IBrandCrudRepository iBrandCrudRepository;
    private final BrandMapper brandMapper;

    public BrandCrudRepositoryImpl(IBrandCrudRepository iBrandCrudRepository, BrandMapper brandMapper) {
        this.iBrandCrudRepository = iBrandCrudRepository;
        this.brandMapper = brandMapper;
    }

    @Override
    public Brand saveBrand(Brand brand) {
        if(existsByName(brand.getName())){
            throw new IllegalArgumentException("La Marca con el nombre " + brand.getName() + " ya existe.");
        }
        return brandMapper.toBrand(iBrandCrudRepository.save(brandMapper.toBrandEntity(brand)));
    }

    @Override
    public boolean existsByName(String name) {
        return iBrandCrudRepository.existsByName(name);
    }

    @Override
    public Page<Brand> findAllBrands(String sortOrder, Pageable pageable) {
        Page<BrandEntity> page;
        if("asc".equalsIgnoreCase(sortOrder)){
            page = iBrandCrudRepository.findAllByOrderByNameAsc(pageable);
        } else {
            page = iBrandCrudRepository.findAllByOrderByNameDesc(pageable);
        }
        return page.map(brandMapper::toBrand);
    }
}
