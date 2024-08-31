package com.chrisferdev.pragmabackend.domain.spi.output;


import com.chrisferdev.pragmabackend.domain.model.Brand;
import com.chrisferdev.pragmabackend.domain.model.PaginatedResult;

public interface IBrandPersistencePort {

    Brand saveBrand(Brand brand);
    boolean existsByName(String name);
    PaginatedResult<Brand> findAllBrands(String sortOrder, int page, int size);

}
