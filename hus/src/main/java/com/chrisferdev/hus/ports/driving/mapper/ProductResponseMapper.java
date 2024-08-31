package com.chrisferdev.hus.ports.driving.mapper;

import com.chrisferdev.hus.domain.model.Product;
import com.chrisferdev.hus.ports.driving.dto.response.ProductResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductResponseMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "quantity", target = "quantity")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "brandId", target = "brandName")
    ProductResponseDTO toProductResponseDTO(Product product);

}
