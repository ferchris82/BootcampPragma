package com.chrisferdev.hus.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Long id;
    private String name;
    private String description;
    private Integer quantity;
    private BigDecimal price;
    private Set<Long> categoryIds;
    private Set<Long> brandIds;
}
