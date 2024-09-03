package com.chrisferdev.hus.mappertest;

import com.chrisferdev.hus.domain.model.Category;
import com.chrisferdev.hus.ports.driven.jpa.entity.CategoryEntity;
import com.chrisferdev.hus.ports.driven.jpa.mapper.CategoryMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryMapperTest {

    private final CategoryMapper categoryMapper = Mappers.getMapper(CategoryMapper.class);

    @Test
    void toCategory_successful() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(1L);
        categoryEntity.setName("Category Name");
        categoryEntity.setDescription("Category Description");

        Category category = categoryMapper.toCategory(categoryEntity);

        assertEquals(1L, category.getId());
        assertEquals("Category Name", category.getName());
        assertEquals("Category Description", category.getDescription());
    }

    @Test
    void toCategoryEntity_successful() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Category Name");
        category.setDescription("Category Description");

        CategoryEntity categoryEntity = categoryMapper.toCategoryEntity(category);

        assertEquals(1L, categoryEntity.getId());
        assertEquals("Category Name", categoryEntity.getName());
        assertEquals("Category Description", categoryEntity.getDescription());
    }

    @Test
    void toCategoryList_successful() {
        CategoryEntity categoryEntity1 = new CategoryEntity();
        categoryEntity1.setId(1L);
        categoryEntity1.setName("Category 1");
        categoryEntity1.setDescription("Description 1");

        CategoryEntity categoryEntity2 = new CategoryEntity();
        categoryEntity2.setId(2L);
        categoryEntity2.setName("Category 2");
        categoryEntity2.setDescription("Description 2");

        Iterable<CategoryEntity> categoryEntities = List.of(categoryEntity1, categoryEntity2);

        Iterable<Category> categories = categoryMapper.toCategoryList(categoryEntities);

        assertEquals(2, ((List<Category>) categories).size());
        assertEquals("Category 1", ((List<Category>) categories).get(0).getName());
        assertEquals("Category 2", ((List<Category>) categories).get(1).getName());
    }
}

