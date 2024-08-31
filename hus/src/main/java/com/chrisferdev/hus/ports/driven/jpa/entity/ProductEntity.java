package com.chrisferdev.hus.ports.driven.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import java.util.Set;

@Entity
@Table(name="products")
@Data
@NoArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Integer quantity;
    private BigDecimal price;

    @ManyToOne
    private BrandEntity brandEntity;

    @ManyToMany
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<CategoryEntity> categoryEntities;

}