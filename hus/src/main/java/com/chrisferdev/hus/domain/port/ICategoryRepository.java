package com.chrisferdev.hus.domain.port;


import com.chrisferdev.hus.domain.model.Category;

public interface ICategoryRepository {
    Category save (Category category);
    boolean existsByName(String name);
}
