package com.chrisferdev.pragmabackend.ports.driven.jpa.adapter;

import com.chrisferdev.pragmabackend.configuration.exception.CategoryAlreadyExistsException;
import com.chrisferdev.pragmabackend.configuration.exception.exceptionhandler.ExceptionResponse;
import com.chrisferdev.pragmabackend.domain.model.Category;
import com.chrisferdev.pragmabackend.domain.model.PaginatedResult;
import com.chrisferdev.pragmabackend.domain.spi.output.ICategoryPersistencePort;
import com.chrisferdev.pragmabackend.ports.driven.jpa.entity.CategoryEntity;
import com.chrisferdev.pragmabackend.ports.driven.jpa.mapper.CategoryMapper;
import com.chrisferdev.pragmabackend.ports.driven.jpa.repository.ICategoryJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryJpaRepositoryImpl implements ICategoryPersistencePort {
    private final ICategoryJpaRepository iCategoryJpaRepository;
    private final CategoryMapper categoryMapper;

    public CategoryJpaRepositoryImpl(ICategoryJpaRepository iCategoryJpaRepository, CategoryMapper categoryMapper) {
        this.iCategoryJpaRepository = iCategoryJpaRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public Category saveCategory(Category category){
        if(existsByName(category.getName())){
            throw new CategoryAlreadyExistsException(ExceptionResponse.CATEGORY_ALREADY_EXISTS.getMessage());
        }

        if(category.getDescription().trim().isEmpty() || category.getName().trim().isEmpty()){
            throw new IllegalArgumentException(ExceptionResponse.INVALID_CATEGORY.getMessage());
        }

        return categoryMapper.toCategory(iCategoryJpaRepository.save(categoryMapper.toCategoryEntity(category)));
    }

    @Override
    public boolean existsByName(String name) {
        return iCategoryJpaRepository.existsByName(name);
    }

    @Override
    public PaginatedResult<Category> findAllCategories(String sortOrder, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, "asc".equalsIgnoreCase(sortOrder) ? Sort.by("name").ascending() : Sort.by("name").descending());
        Page<CategoryEntity> pageResult = iCategoryJpaRepository.findAll(pageable);
        List<Category> categories = pageResult.getContent().stream()
                .map(categoryMapper::toCategory)
                .toList();
        return new PaginatedResult<>(categories, pageResult.getNumber(), pageResult.getSize(), pageResult.getTotalElements());
    }
}