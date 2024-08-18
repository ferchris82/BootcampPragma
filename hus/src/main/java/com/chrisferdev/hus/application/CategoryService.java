package com.chrisferdev.hus.application;

import com.chrisferdev.hus.domain.model.Category;
import com.chrisferdev.hus.domain.port.ICategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class CategoryService {
    private final ICategoryRepository iCategoryRepository;

    public CategoryService(ICategoryRepository iCategoryRepository) {
        this.iCategoryRepository = iCategoryRepository;
    }

    //Método Crud
    public Category save(Category category){
        return iCategoryRepository.save(category);
    }

    public Page<Category> findAllCategories(String sortOrder, Pageable pageable) {
        return iCategoryRepository.findAllCategories(sortOrder, pageable);
    }


}
