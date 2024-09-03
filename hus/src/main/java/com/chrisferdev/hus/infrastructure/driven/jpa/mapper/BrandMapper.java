package com.chrisferdev.hus.infrastructure.driven.jpa.mapper;


import com.chrisferdev.hus.domain.model.Brand;
import com.chrisferdev.hus.infrastructure.driven.jpa.entity.BrandEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    Brand toBrand(BrandEntity brandEntity);

    Iterable<Brand> toBrandList(Iterable<BrandEntity> brandEntities);

    @InheritInverseConfiguration
    BrandEntity toBrandEntity(Brand brand);
}
