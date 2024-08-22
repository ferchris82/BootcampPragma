package com.chrisferdev.hus.hus2;


import com.chrisferdev.hus.application.CategoryService;
import com.chrisferdev.hus.domain.model.Category;
import com.chrisferdev.hus.domain.port.ICategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
class TestingH2 {

    ICategoryRepository iCategoryRepository;
    CategoryService categoryService;

    @BeforeEach
    void setUp(){
        iCategoryRepository = mock(ICategoryRepository.class);
        categoryService = new CategoryService(iCategoryRepository);
    }

    @Test
    void findAllCategories() {
        // Arrange
        Category category = new Category();
        category.setName("Category 1");
        List<Category> categories = List.of(category);
        Page<Category> categoryPage = new PageImpl<>(categories);
        Pageable pageable = Pageable.ofSize(10);

        when(iCategoryRepository.findAllCategories("asc", pageable)).thenReturn(categoryPage);

        // Act
        Page<Category> result = categoryService.findAllCategories("asc", pageable);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals("Category 1", result.getContent().get(0).getName());
        verify(iCategoryRepository, times(1)).findAllCategories("asc", pageable);
    }
}
