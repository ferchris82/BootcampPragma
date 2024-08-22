package com.chrisferdev.hus.application;

import com.chrisferdev.hus.domain.model.Brand;
import com.chrisferdev.hus.domain.port.IBrandRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class BrandService {
    private final IBrandRepository iBrandRepository;

    public BrandService(IBrandRepository iBrandRepository) {
        this.iBrandRepository = iBrandRepository;
    }

    public Brand saveBrand(Brand brand){
        return iBrandRepository.saveBrand(brand);
    }

    // Método para paginar la lista de categorías
    public Page<Brand> findAllBrands(String sortOrder, Pageable pageable) {
        return iBrandRepository.findAllBrands(sortOrder, pageable);
    }
}
