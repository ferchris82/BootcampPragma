package com.chrisferdev.hus.domain.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderProduct {
    private Long id;
    private BigDecimal quantity;
    private BigDecimal price;
    private Long productId;

    public BigDecimal getTotalItem(){
        return this.price.multiply(quantity);
    }
}
