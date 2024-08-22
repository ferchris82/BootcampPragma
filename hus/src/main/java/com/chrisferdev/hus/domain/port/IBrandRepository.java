package com.chrisferdev.hus.domain.port;

import com.chrisferdev.hus.domain.model.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBrandRepository {
    Brand saveBrand(Brand brand);
    boolean existsByName(String name);
    Page<Brand>findAllBrands(String sortOrder, Pageable pageable);
}
