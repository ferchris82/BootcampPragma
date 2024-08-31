package com.chrisferdev.pragmabackend.domain.api.usecase;


// Implementación de los casos de uso

import com.chrisferdev.pragmabackend.configuration.exception.CategoryAlreadyExistsException;
import com.chrisferdev.pragmabackend.domain.model.Category;
import com.chrisferdev.pragmabackend.domain.model.PaginatedResult;
import com.chrisferdev.pragmabackend.domain.spi.output.ICategoryPersistencePort;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl {
    private final ICategoryPersistencePort iCategoryPersistencePort;

    public CategoryServiceImpl(ICategoryPersistencePort iCategoryPersistencePort) {
        this.iCategoryPersistencePort = iCategoryPersistencePort;
    }

    // Métodos
    public Category saveCategory(Category category){
        if(iCategoryPersistencePort.existsByName(category.getName())){
            throw new CategoryAlreadyExistsException("La categoría con el nombre " + category.getName() + " ya existe.");

        }
        return iCategoryPersistencePort.saveCategory(category);
    }

    public PaginatedResult<Category> findAllCategories(String sortOrder, int page, int size) {
        return iCategoryPersistencePort.findAllCategories(sortOrder, page, size);
    }
}