package com.chrisferdev.hus.usecase;

import com.chrisferdev.hus.domain.api.usecase.BrandServiceImpl;
import com.chrisferdev.hus.domain.model.Brand;
import com.chrisferdev.hus.domain.model.PaginatedResult;
import com.chrisferdev.hus.domain.spi.output.IBrandPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class BrandServiceImplTest {
  private IBrandPersistencePort iBrandPersistencePort;
  private BrandServiceImpl brandService;

  @BeforeEach
  void setUp() {
   iBrandPersistencePort = mock(IBrandPersistencePort.class);
   brandService = new BrandServiceImpl(iBrandPersistencePort);
  }

  @Test
  void saveBrand_validBrand_success() {
   // Preparar datos de prueba
   Brand brand = new Brand();
   brand.setId(1L);
   brand.setName("BrandName");

   // Configurar comportamiento de mocks
   when(iBrandPersistencePort.saveBrand(brand)).thenReturn(brand);

   // Ejecutar el método a probar
   Brand result = brandService.saveBrand(brand);

   // Verificar el resultado
   assertNotNull(result);
   assertEquals(1L, result.getId());
   assertEquals("BrandName", result.getName());
   verify(iBrandPersistencePort).saveBrand(brand);
  }

  @Test
  void findAllBrands_validInput_success() {
   // Preparar datos de prueba
   PaginatedResult<Brand> paginatedResult = new PaginatedResult<>();
   paginatedResult.setItems(List.of(new Brand(), new Brand()));
   paginatedResult.setTotal(2);

   // Configurar comportamiento de mocks
   when(iBrandPersistencePort.findAllBrands("asc", 0, 10)).thenReturn(paginatedResult);

   // Ejecutar el método a probar
   PaginatedResult<Brand> result = brandService.findAllBrands("asc", 0, 10);

   // Verificar el resultado
   assertNotNull(result);
   assertEquals(2, result.getTotal());
   assertEquals(2, result.getItems().size());
   verify(iBrandPersistencePort).findAllBrands("asc", 0, 10);
  }
}
