package com.chrisferdev.hus.infrastructure.driven.jpa.mapper;

import com.chrisferdev.hus.domain.model.OrderProduct;
import com.chrisferdev.hus.infrastructure.driven.jpa.entity.OrderProductEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderProductMapper {

    @Mapping(source = "orderEntity.id", target = "id")
    OrderProduct toOrderProduct(OrderProductEntity orderProductEntity);
    Iterable<OrderProduct> toOrderProductList(Iterable<OrderProductEntity> orderProductEntities);

    @Mapping(target = "orderEntity", ignore = true)
    OrderProductEntity toOrderProductEntity(OrderProduct orderProduct);
}
