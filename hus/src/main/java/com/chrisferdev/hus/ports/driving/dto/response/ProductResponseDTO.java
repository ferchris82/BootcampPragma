package com.chrisferdev.hus.ports.driving.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {
    private String name;
    private String description;
    private Integer quantity;
    private BigDecimal price;
    private String brandName;
}
