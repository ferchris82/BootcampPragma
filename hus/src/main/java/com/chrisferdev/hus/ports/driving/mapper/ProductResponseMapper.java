package com.chrisferdev.hus.ports.driving.mapper;

import com.chrisferdev.hus.domain.model.Product;
import com.chrisferdev.hus.ports.driven.jpa.entity.CategoryEntity;
import com.chrisferdev.hus.ports.driven.jpa.entity.ProductEntity;
import com.chrisferdev.hus.ports.driving.dto.response.ProductResponseDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductResponseMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "quantity", target = "quantity")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "brandEntity.name", target = "brandName")
    @Mapping(source = "categoryEntities", target = "categoryIds")
    @Mapping(source = "categoryEntities", target = "categoryNames")
    ProductResponseDTO toProductResponseDTO(ProductEntity productEntity);

    Iterable<Product> toProductResponseDTOList(Iterable<ProductEntity> productEntities);

    @InheritInverseConfiguration
    @Mapping(target = "categoryEntities", source = "categoryIds")
    ProductEntity toProductEntity(Product product);

    default List<Long> mapCategories(List<CategoryEntity> categoryEntities) {
        if (categoryEntities == null) {
            return List.of();  // Retorna una lista vacía si categoryEntities es null
        }
        return categoryEntities.stream()
                .map(CategoryEntity::getId)
                .toList();
    }

    default List<CategoryEntity> mapCategoryIds(List<Long> categoryIds) {
        if (categoryIds == null) {
            return List.of();  // Retorna una lista vacía si categoryIds es null
        }
        return categoryIds.stream()
                .map(id -> {
                    CategoryEntity category = new CategoryEntity();
                    category.setId(id);
                    return category;
                })
                .toList();
    }

    default List<String> mapCategoriesToNames(List<CategoryEntity> categoryEntities) {
        if (categoryEntities == null) {
            return List.of();  // Retorna una lista vacía si categoryEntities es null
        }
        return categoryEntities.stream()
                .map(CategoryEntity::getName)
                .toList();
    }
}
