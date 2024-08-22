package com.chrisferdev.hus.hus1;

import com.chrisferdev.hus.application.CategoryService;
import com.chrisferdev.hus.domain.model.Category;
import com.chrisferdev.hus.domain.port.ICategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
class TestingH1 {

	ICategoryRepository iCategoryRepository;
	CategoryService categoryService;

	@BeforeEach
	void setUp(){
		iCategoryRepository = mock(ICategoryRepository.class);
		categoryService = new CategoryService(iCategoryRepository);
	}

	@Test
	void saveCategory(){
		// Arrange
		Category category = new Category();
		category.setName("Test Category");
		Category savedCategory = new Category();
		savedCategory.setName("Test Category");

		// Mock behavior Crea el objeto
		when(iCategoryRepository.saveCategory(category)).thenReturn(savedCategory);

		// Act Llama al método
		Category result = categoryService.saveCategory(category);

		// Assert ** Verifica el resultado
		assertNotNull(result);
		assertEquals("Test Category", result.getName());
		verify(iCategoryRepository, times(1)).saveCategory(category);
	}
	@Test
	void existsByName(){
		// Arrange
		String categoryName = "Test Category";
		boolean exists = true;

		// Mock behavior
		when(iCategoryRepository.existsByName(categoryName)).thenReturn(exists);

		// Act
		boolean result = iCategoryRepository.existsByName(categoryName);

		// Assert
		assertTrue(result);
		verify(iCategoryRepository, times(1)).existsByName(categoryName);
	}


}
