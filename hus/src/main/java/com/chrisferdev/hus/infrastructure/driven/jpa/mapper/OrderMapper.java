package com.chrisferdev.hus.infrastructure.driven.jpa.mapper;

import com.chrisferdev.hus.domain.model.Order;
import com.chrisferdev.hus.infrastructure.driven.jpa.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = OrderProductMapper.class)
public interface OrderMapper {

    @Mapping(source = "userEntity.id", target = "id")
    @Mapping(source = "orderProducts", target = "orderProducts")
    Order toOrder(OrderEntity orderEntity);
    Iterable<Order> toOrderList(Iterable<OrderEntity> orderEntities);

    @Mapping(target = "userEntity", ignore = true) 
    @Mapping(target = "orderProducts", ignore = true)
    OrderEntity toOrderEntity(Order order);
}
