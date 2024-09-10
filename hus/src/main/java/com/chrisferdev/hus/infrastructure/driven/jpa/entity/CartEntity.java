package com.chrisferdev.hus.infrastructure.driven.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
@Data
@NoArgsConstructor
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cart_id")
    private List<CartItemEntity> items = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private CartStatus status = CartStatus.PENDING;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;


}