package com.chrisferdev.hus.infrastructure.driven.jpa.adapter;

import com.chrisferdev.hus.domain.model.Order;
import com.chrisferdev.hus.domain.model.OrderState;
import com.chrisferdev.hus.domain.model.PaginatedResult;
import com.chrisferdev.hus.domain.spi.output.IOrderPersistencePort;
import com.chrisferdev.hus.infrastructure.driven.jpa.entity.OrderEntity;
import com.chrisferdev.hus.infrastructure.driven.jpa.entity.UserEntity;
import com.chrisferdev.hus.infrastructure.driven.jpa.mapper.OrderMapper;
import com.chrisferdev.hus.infrastructure.driven.jpa.repository.IOrderJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderJpaRepositoryImpl implements IOrderPersistencePort {

    private final OrderMapper orderMapper;
    private final IOrderJpaRepository iOrderJpaRepository;

    public OrderJpaRepositoryImpl(OrderMapper orderMapper, IOrderJpaRepository iOrderJpaRepository) {
        this.orderMapper = orderMapper;
        this.iOrderJpaRepository = iOrderJpaRepository;
    }

    @Override
    public Order saveOrder(Order order) {
        OrderEntity orderEntity = orderMapper.toOrderEntity(order);

        orderEntity.getOrderProducts().forEach(
                orderProductEntity -> orderProductEntity.setOrderEntity(orderEntity)
        );
        return orderMapper.toOrder(iOrderJpaRepository.save(orderEntity));
    }

    @Override
    public Order findById(Long id) {
        return orderMapper.toOrder(iOrderJpaRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Orden con id: "+ id+" no encontrado")
        ));
    }

    @Override
    public PaginatedResult<Order> findAll(String sortOrder, int page, int size) {
        Sort sort = sortOrder.equalsIgnoreCase("asc") ? Sort.by("dateCreated").ascending() : Sort.by("dateCreated").descending();

        Pageable pageable = PageRequest.of(page, size, sort);


        Page<OrderEntity> orderPage = iOrderJpaRepository.findAll(pageable);


        List<Order> orders = orderPage.stream()
                .map(orderMapper::toOrder)
                .toList();

        return new PaginatedResult<>(orders, orderPage.getNumber(), orderPage.getSize(), orderPage.getTotalElements());
    }

    @Override
    public PaginatedResult<Order> findByUserId(Long id, String sortOrder, int page, int size) {

        Sort sort = sortOrder.equalsIgnoreCase("asc") ? Sort.by("dateCreated").ascending() : Sort.by("dateCreated").descending();

        Pageable pageable = PageRequest.of(page, size, sort);


        UserEntity userEntity = new UserEntity();
        userEntity.setId(id);


        Page<OrderEntity> orderPage = iOrderJpaRepository.findByUserEntity(userEntity, pageable);


        List<Order> orders = orderPage.stream()
                .map(orderMapper::toOrder)
                .toList();

        return new PaginatedResult<>(orders, orderPage.getNumber(), orderPage.getSize(), orderPage.getTotalElements());
    }

    @Override
    public void updateStateById(Long id, String state) {
        if(state.equals(OrderState.CANCELLED)){
            iOrderJpaRepository.updateStateById(id,OrderState.CANCELLED);
        }else{
            iOrderJpaRepository.updateStateById(id,OrderState.CONFIRMED);
        }
    }
}
