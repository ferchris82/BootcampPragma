package com.chrisferdev.hus.infrastructure.driven.jpa.repository;

import com.chrisferdev.hus.domain.model.OrderState;
import com.chrisferdev.hus.infrastructure.driven.jpa.entity.OrderEntity;
import com.chrisferdev.hus.infrastructure.driven.jpa.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface IOrderJpaRepository extends JpaRepository<OrderEntity, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE OrderEntity o SET o.orderState = :state WHERE o.id = :id")
    void updateStateById(Long id, OrderState state);

    Page<OrderEntity> findByUserEntity(UserEntity userEntity, Pageable pageable);
}
