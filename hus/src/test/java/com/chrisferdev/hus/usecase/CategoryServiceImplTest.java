package com.chrisferdev.hus.usecase;

import com.chrisferdev.hus.configuration.exception.CategoryAlreadyExistsException;
import com.chrisferdev.hus.domain.api.usecase.CategoryServiceImpl;
import com.chrisferdev.hus.domain.model.Category;
import com.chrisferdev.hus.domain.model.PaginatedResult;
import com.chrisferdev.hus.domain.spi.output.ICategoryPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceImplTest {
    private ICategoryPersistencePort iCategoryPersistencePort;
    private CategoryServiceImpl categoryService;

    @BeforeEach
    void setUp() {
        iCategoryPersistencePort = mock(ICategoryPersistencePort.class);
        categoryService = new CategoryServiceImpl(iCategoryPersistencePort);
    }

    @Test
    void saveCategory_validCategory_success() {
        // Preparar datos de prueba
        Category category = new Category();
        category.setId(1L);
        category.setName("CategoryName");

        // Configurar comportamiento de mocks
        when(iCategoryPersistencePort.existsByName(category.getName())).thenReturn(false);
        when(iCategoryPersistencePort.saveCategory(category)).thenReturn(category);

        // Ejecutar el método a probar
        Category result = categoryService.saveCategory(category);

        // Verificar el resultado
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("CategoryName", result.getName());
        verify(iCategoryPersistencePort).existsByName(category.getName());
        verify(iCategoryPersistencePort).saveCategory(category);
    }

    @Test
    void saveCategory_categoryAlreadyExists_throwsException() {
        // Preparar datos de prueba
        Category category = new Category();
        category.setName("CategoryName");

        // Configurar comportamiento de mocks
        when(iCategoryPersistencePort.existsByName(category.getName())).thenReturn(true);

        CategoryAlreadyExistsException thrown = assertThrows(
                CategoryAlreadyExistsException.class,
                () -> categoryService.saveCategory(category)
        );
        assertEquals("La categoría con el nombre CategoryName ya existe.", thrown.getMessage());

        // Verificar que no se llamó al método saveCategory
        verify(iCategoryPersistencePort, never()).saveCategory(category);
    }

    @Test
    void findAllCategories_validInput_success() {
        // Preparar datos de prueba
        PaginatedResult<Category> paginatedResult = new PaginatedResult<>();
        paginatedResult.setItems(List.of(new Category(), new Category()));
        paginatedResult.setTotalElements(2);
        paginatedResult.setSize(10);  // Asegúrate de definir el tamaño adecuado

        // Configurar comportamiento de mocks
        when(iCategoryPersistencePort.findAllCategories("asc", 0, 10)).thenReturn(paginatedResult);

        // Ejecutar el método a probar
        PaginatedResult<Category> result = categoryService.findAllCategories("asc", 0, 10);

        // Verificar el resultado
        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        assertEquals(2, result.getItems().size());
        verify(iCategoryPersistencePort).findAllCategories("asc", 0, 10);
    }
}
