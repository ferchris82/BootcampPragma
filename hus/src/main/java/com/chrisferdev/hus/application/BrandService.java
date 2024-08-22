package com.chrisferdev.hus.application;

import com.chrisferdev.hus.domain.model.Brand;
import com.chrisferdev.hus.domain.port.IBrandRepository;

public class BrandService {
    private final IBrandRepository iBrandRepository;

    public BrandService(IBrandRepository iBrandRepository) {
        this.iBrandRepository = iBrandRepository;
    }

    public Brand saveBrand(Brand brand){
        return iBrandRepository.saveBrand(brand);
    }
}
