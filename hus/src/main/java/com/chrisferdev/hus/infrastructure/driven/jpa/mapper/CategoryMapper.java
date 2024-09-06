package com.chrisferdev.hus.infrastructure.driven.jpa.mapper;


import com.chrisferdev.hus.domain.model.Category;
import com.chrisferdev.hus.infrastructure.driven.jpa.entity.CategoryEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "products", ignore = true)
    Category toCategory(CategoryEntity categoryEntity);

    Iterable<Category> toCategoryList(Iterable<CategoryEntity> categoryEntities);
    
    @InheritInverseConfiguration
    @Mapping(target = "products", ignore = true)
    CategoryEntity toCategoryEntity(Category category);
}