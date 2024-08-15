package com.chrisferdev.hus.infrastructure.adapter;

import com.chrisferdev.hus.domain.model.Category;
import com.chrisferdev.hus.domain.port.ICategoryRepository;
import com.chrisferdev.hus.infrastructure.mapper.CategoryMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryCrudRepositoryImpl implements ICategoryRepository {
    private final ICategoryCrudRepository iCategoryCrudRepository;
    private final CategoryMapper categoryMapper;

    public CategoryCrudRepositoryImpl(ICategoryCrudRepository iCategoryCrudRepository, CategoryMapper categoryMapper) {
        this.iCategoryCrudRepository = iCategoryCrudRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public Category save(Category category) {
        if(existsByName(category.getName())) {
            throw new IllegalArgumentException("La categoría con el nombre " + category.getName() + " ya existe.");
        }
        return categoryMapper.toCategory(iCategoryCrudRepository.save(categoryMapper.toCategoryEntity(category)));
    }

    @Override
    public boolean existsByName(String name) {
        return iCategoryCrudRepository.existsByName(name);
    }
}
