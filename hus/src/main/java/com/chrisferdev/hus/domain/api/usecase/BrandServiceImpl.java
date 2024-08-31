package com.chrisferdev.hus.domain.api.usecase;

import com.chrisferdev.hus.configuration.exception.CategoryAlreadyExistsException;
import com.chrisferdev.hus.domain.model.Brand;
import com.chrisferdev.hus.domain.model.PaginatedResult;
import com.chrisferdev.hus.domain.spi.output.IBrandPersistencePort;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl {
    private final IBrandPersistencePort iBrandPersistencePort;

    public BrandServiceImpl(IBrandPersistencePort iBrandPersistencePort) {
        this.iBrandPersistencePort = iBrandPersistencePort;
    }


    public Brand saveBrand(Brand brand){
        if(iBrandPersistencePort.existsByName(brand.getName())){
            throw new CategoryAlreadyExistsException("La categoría con el nombre " + brand.getName() + " ya existe.");

        }
        return iBrandPersistencePort.saveBrand(brand);
    }

    public PaginatedResult<Brand> findAllBrands(String sortOrder, int page, int size) {
        return iBrandPersistencePort.findAllBrands(sortOrder, page, size);
    }
}
