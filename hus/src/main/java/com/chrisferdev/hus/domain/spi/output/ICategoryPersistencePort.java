package com.chrisferdev.hus.domain.spi.output;

//SPI (Service Provider Interface)

import com.chrisferdev.hus.domain.model.Category;
import com.chrisferdev.hus.domain.model.PaginatedResult;

public interface ICategoryPersistencePort {
    Category saveCategory (Category category);
    boolean existsByName(String name);
    PaginatedResult<Category> findAllCategories(String sortOrder, int page, int size);
}
