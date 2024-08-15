package com.chrisferdev.hus.application;

import com.chrisferdev.hus.domain.model.Category;
import com.chrisferdev.hus.domain.port.ICategoryRepository;

public class CategoryService {
    private final ICategoryRepository iCategoryRepository;

    public CategoryService(ICategoryRepository iCategoryRepository) {
        this.iCategoryRepository = iCategoryRepository;
    }

    //Método Crud
    public Category save(Category category){
        return iCategoryRepository.save(category);
    }

}
