package com.chrisferdev.hus.infrastructure.mapper;

import com.chrisferdev.hus.domain.model.Category;
import com.chrisferdev.hus.infrastructure.entity.CategoryEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    Category toCategory(CategoryEntity categoryEntity);

    Iterable<Category> toCategoryList(Iterable<CategoryEntity> categoryEntities);

    @InheritInverseConfiguration
    // Hace una inversión de target a source
    CategoryEntity toCategoryEntity(Category category);
}
