package com.chrisferdev.hus.mappertest;

import com.chrisferdev.hus.domain.model.Product;
import com.chrisferdev.hus.infrastructure.driven.jpa.entity.CategoryEntity;
import com.chrisferdev.hus.infrastructure.driven.jpa.entity.ProductEntity;
import com.chrisferdev.hus.infrastructure.driven.jpa.mapper.ProductMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductMapperTest {
     private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

     @Test
     void toProduct_successful() {
         ProductEntity productEntity = new ProductEntity();
         productEntity.setId(1L);
         productEntity.setName("Product Name");
         productEntity.setDescription("Product Description");
         productEntity.setQuantity(10);
         productEntity.setPrice(BigDecimal.valueOf(99.99));

         CategoryEntity categoryEntity1 = new CategoryEntity();
         categoryEntity1.setId(1L);
         categoryEntity1.setName("Category 1");

         CategoryEntity categoryEntity2 = new CategoryEntity();
         categoryEntity2.setId(2L);
         categoryEntity2.setName("Category 2");

         productEntity.setCategoryEntities(List.of(categoryEntity1, categoryEntity2));

         Product product = productMapper.toProduct(productEntity);

         assertEquals(1L, product.getId());
         assertEquals("Product Name", product.getName());
         assertEquals("Product Description", product.getDescription());
         assertEquals(10, product.getQuantity());
         assertEquals(BigDecimal.valueOf(99.99), product.getPrice());
         assertEquals(List.of(1L, 2L), product.getCategoryIds());
         assertEquals(List.of("Category 1", "Category 2"), product.getCategoryNames());
     }

     @Test
     void toProductEntity_successful() {
         Product product = new Product();
         product.setId(1L);
         product.setName("Product Name");
         product.setDescription("Product Description");
         product.setQuantity(10);
         product.setPrice(BigDecimal.valueOf(99.99));
         product.setCategoryIds(List.of(1L, 2L));

         ProductEntity productEntity = productMapper.toProductEntity(product);

         assertEquals(1L, productEntity.getId());
         assertEquals("Product Name", productEntity.getName());
         assertEquals("Product Description", productEntity.getDescription());
         assertEquals(10, productEntity.getQuantity());
         assertEquals(BigDecimal.valueOf(99.99), product.getPrice());
         assertEquals(List.of(1L, 2L), productMapper.mapCategories(productEntity.getCategoryEntities()));
     }

     @Test
     void toProductList_successful() {
         ProductEntity productEntity1 = new ProductEntity();
         productEntity1.setId(1L);
         productEntity1.setName("Product 1");
         productEntity1.setDescription("Description 1");

         ProductEntity productEntity2 = new ProductEntity();
         productEntity2.setId(2L);
         productEntity2.setName("Product 2");
         productEntity2.setDescription("Description 2");

         Iterable<ProductEntity> productEntities = List.of(productEntity1, productEntity2);

         Iterable<Product> products = productMapper.toProductList(productEntities);

         assertEquals(2, ((List<Product>) products).size());
         assertEquals("Product 1", ((List<Product>) products).get(0).getName());
         assertEquals("Product 2", ((List<Product>) products).get(1).getName());
     }

     @Test
     void mapCategories_successful() {
         CategoryEntity categoryEntity1 = new CategoryEntity();
         categoryEntity1.setId(1L);

         CategoryEntity categoryEntity2 = new CategoryEntity();
         categoryEntity2.setId(2L);

         List<Long> categoryIds = productMapper.mapCategories(List.of(categoryEntity1, categoryEntity2));

         assertEquals(List.of(1L, 2L), categoryIds);
     }

     @Test
     void mapCategoryIds_successful() {
         List<Long> categoryIds = List.of(1L, 2L);

         List<CategoryEntity> categoryEntities = productMapper.mapCategoryIds(categoryIds);

         assertEquals(2, categoryEntities.size());
         assertEquals(1L, categoryEntities.get(0).getId());
         assertEquals(2L, categoryEntities.get(1).getId());
     }

     @Test
     void mapCategoriesToNames_successful() {
         CategoryEntity categoryEntity1 = new CategoryEntity();
         categoryEntity1.setName("Category 1");

         CategoryEntity categoryEntity2 = new CategoryEntity();
         categoryEntity2.setName("Category 2");

         List<String> categoryNames = productMapper.mapCategoriesToNames(List.of(categoryEntity1, categoryEntity2));

         assertEquals(List.of("Category 1", "Category 2"), categoryNames);
     }
 }
