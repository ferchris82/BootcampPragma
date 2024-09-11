package com.chrisferdev.hus.infrastructure.driven.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "order_products")
public class OrderProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal quantity;
    private BigDecimal price;
    private Long productId;

    @ManyToOne
    private OrderEntity orderEntity;
}
