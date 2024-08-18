package com.chrisferdev.hus.infrastructure.adapter;

import com.chrisferdev.hus.domain.model.Category;
import com.chrisferdev.hus.domain.port.ICategoryRepository;
import com.chrisferdev.hus.infrastructure.entity.CategoryEntity;
import com.chrisferdev.hus.infrastructure.mapper.CategoryMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        if (existsByName(category.getName())) {
            throw new IllegalArgumentException("La categoría con el nombre " + category.getName() + " ya existe.");
        }
        return categoryMapper.toCategory(iCategoryCrudRepository.save(categoryMapper.toCategoryEntity(category)));
    }

    @Override
    public boolean existsByName(String name) {
        return iCategoryCrudRepository.existsByName(name);
    }

    @Override
    public Page<Category> findAllCategories(String sortOrder, Pageable pageable) {
        Page<CategoryEntity> page;
        if ("asc".equalsIgnoreCase(sortOrder)) {
            page = iCategoryCrudRepository.findAllByOrderByNameAsc(pageable);
        } else {
            page = iCategoryCrudRepository.findAllByOrderByNameDesc(pageable);
        }
        return page.map(categoryMapper::toCategory);
    }


}
