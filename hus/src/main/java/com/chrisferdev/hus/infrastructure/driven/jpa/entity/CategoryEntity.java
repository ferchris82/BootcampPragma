package com.chrisferdev.hus.infrastructure.driven.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String name;
    @Column(length = 90)
    private String description;

    @ManyToMany(mappedBy = "categoryEntities")
    private List<ProductEntity> products;
}
