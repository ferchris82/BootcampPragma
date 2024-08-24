package com.chrisferdev.hus.infrastructure.mapper;

import com.chrisferdev.hus.domain.model.Product;
import com.chrisferdev.hus.infrastructure.entity.CategoryEntity;
import com.chrisferdev.hus.infrastructure.entity.ProductEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProductMapper {


    @Mapping(source = "id", target = "id")
    @Mapping(source = "categoryEntities", target = "categoryIds")
    Product toProduct(ProductEntity productEntity);

    Iterable<Product> toProductList(Iterable<ProductEntity> productEntities);

    @InheritInverseConfiguration
    @Mapping(target = "categoryEntities", source = "categoryIds")
    ProductEntity toProductEntity(Product product);

    default Set<Long> mapCategories(Set<CategoryEntity> categoryEntities) {
        return categoryEntities.stream()
                .map(CategoryEntity::getId)
                .collect(Collectors.toSet());
    }

    default Set<CategoryEntity> mapCategoryIds(Set<Long> categoryIds) {
        return categoryIds.stream()
                .map(id -> {
                    CategoryEntity category = new CategoryEntity();
                    category.setId(id);
                    return category;
                })
                .collect(Collectors.toSet());
    }
}
