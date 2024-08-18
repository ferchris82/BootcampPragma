package com.chrisferdev.hus.domain.port;


import com.chrisferdev.hus.domain.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICategoryRepository {
    Category save (Category category);
    boolean existsByName(String name);
    Page<Category> findAllCategories(String sortOrder, Pageable pageable);

}
