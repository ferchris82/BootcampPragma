package com.chrisferdev.hus.ports.driving.mapper;


import com.chrisferdev.hus.ports.driven.jpa.entity.ProductEntity;
import com.chrisferdev.hus.ports.driving.dto.request.ProductRequestDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface ProductRequestMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "quantity", target = "quantity")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "brandId", target = "brandEntity.id")
    ProductEntity toProductEntity(ProductRequestDTO productRequestDTO);

    @InheritInverseConfiguration
    ProductRequestDTO toProductRequestDTO(ProductEntity productEntity);
}
