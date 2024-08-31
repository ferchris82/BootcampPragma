package com.chrisferdev.hus.ports.driven.jpa.mapper;

import com.chrisferdev.hus.domain.model.Product;
import com.chrisferdev.hus.ports.driven.jpa.entity.CategoryEntity;
import com.chrisferdev.hus.ports.driven.jpa.entity.ProductEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "quantity", target = "quantity")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "brandEntity.id", target = "brandId")
    @Mapping(source = "categoryEntities", target = "categoryIds")
    Product toProduct(ProductEntity productEntity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "quantity", target = "quantity")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "brandId", target = "brandEntity.id")
    @Mapping(source = "categoryIds", target = "categoryEntities")
    ProductEntity toProductEntity(Product product);

    List<Product> toProductList(List<ProductEntity> productEntities);

    List<ProductEntity> toProductEntityList(List<Product> products);

    default List<Long> mapCategories(List<CategoryEntity> categoryEntities) {
        return categoryEntities.stream()
                .map(CategoryEntity::getId)
                .toList();
    }

    default List<CategoryEntity> mapCategoryIds(List<Long> categoryIds) {
        return categoryIds.stream()
                .map(id -> {
                    CategoryEntity category = new CategoryEntity();
                    category.setId(id);
                    return category;
                })
                .toList();
    }
}
