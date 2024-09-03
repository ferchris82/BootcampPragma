package com.chrisferdev.hus.infrastructure.driving.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {
    private String name;
    private String description;
    private Integer quantity;
    private BigDecimal price;

    private String brandName;
    private List<Long> categoryIds;
    private List<String> categoryNames;
}
