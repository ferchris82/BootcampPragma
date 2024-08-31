package com.chrisferdev.hus.domain.spi.output;


import com.chrisferdev.hus.domain.model.Brand;
import com.chrisferdev.hus.domain.model.PaginatedResult;

public interface IBrandPersistencePort {

    Brand saveBrand(Brand brand);
    boolean existsByName(String name);
    PaginatedResult<Brand> findAllBrands(String sortOrder, int page, int size);

}
