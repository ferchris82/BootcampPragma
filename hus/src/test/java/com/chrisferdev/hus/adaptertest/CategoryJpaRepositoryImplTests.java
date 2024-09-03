package com.chrisferdev.hus.adaptertest;

import com.chrisferdev.hus.configuration.exception.CategoryAlreadyExistsException;
import com.chrisferdev.hus.domain.model.Category;
import com.chrisferdev.hus.domain.model.PaginatedResult;
import com.chrisferdev.hus.ports.driven.jpa.adapter.CategoryJpaRepositoryImpl;
import com.chrisferdev.hus.ports.driven.jpa.entity.CategoryEntity;
import com.chrisferdev.hus.ports.driven.jpa.mapper.CategoryMapper;
import com.chrisferdev.hus.ports.driven.jpa.repository.ICategoryJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CategoryJpaRepositoryImplTest {

    private ICategoryJpaRepository iCategoryJpaRepository;
    private CategoryMapper categoryMapper;
    private CategoryJpaRepositoryImpl categoryJpaRepositoryImpl;

    @BeforeEach
    void setUp() {
        iCategoryJpaRepository = mock(ICategoryJpaRepository.class);
        categoryMapper = mock(CategoryMapper.class);
        categoryJpaRepositoryImpl = new CategoryJpaRepositoryImpl(iCategoryJpaRepository, categoryMapper);
    }

    @Test
    void saveCategory_successful() {
        Category category = new Category();
        category.setName("Category 1");
        category.setDescription("Description");

        CategoryEntity categoryEntity = new CategoryEntity();
        when(categoryMapper.toCategoryEntity(any(Category.class))).thenReturn(categoryEntity);
        when(iCategoryJpaRepository.save(any(CategoryEntity.class))).thenReturn(categoryEntity);
        when(categoryMapper.toCategory(any(CategoryEntity.class))).thenReturn(category);

        Category result = categoryJpaRepositoryImpl.saveCategory(category);

        assertNotNull(result);
        assertEquals("Category 1", result.getName());
        verify(iCategoryJpaRepository).save(categoryEntity);
    }

    @Test
    void saveCategory_errorCategoryAlreadyExists() {
        Category category = new Category();
        category.setName("Category 1");

        when(iCategoryJpaRepository.existsByName("Category 1")).thenReturn(true);

        assertThrows(CategoryAlreadyExistsException.class, () -> categoryJpaRepositoryImpl.saveCategory(category));
    }

    @Test
    void saveCategory_errorEmptyDescription() {
        Category category = new Category();
        category.setName("Category 1");
        category.setDescription(" ");

        assertThrows(IllegalArgumentException.class, () -> categoryJpaRepositoryImpl.saveCategory(category));
    }

    @Test
    void saveCategory_errorEmptyName() {
        Category category = new Category();
        category.setName(" ");
        category.setDescription("Description");

        assertThrows(IllegalArgumentException.class, () -> categoryJpaRepositoryImpl.saveCategory(category));
    }

    @Test
    void findAllCategories_successful() {
        Pageable pageable = PageRequest.of(0, 1, Sort.by("name").ascending());
        CategoryEntity categoryEntity = new CategoryEntity();
        Category category = new Category();
        Page<CategoryEntity> categoryPage = new PageImpl<>(List.of(categoryEntity));

        when(iCategoryJpaRepository.findAll(pageable)).thenReturn(categoryPage);
        when(categoryMapper.toCategory(any(CategoryEntity.class))).thenReturn(category);

        PaginatedResult<Category> result = categoryJpaRepositoryImpl.findAllCategories("asc", 0, 1);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(1, result.getItems().size());
        verify(iCategoryJpaRepository).findAll(pageable);
    }
}