package com.chrisferdev.hus.ports.driving.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {
    @NotBlank(message = "The product name is required.")
    private String name;

    @NotBlank(message = "The product description is required.")
    private String description;

    @NotNull(message = "The quantity is required.")
    @Positive(message = "The quantity must be a positive number.")
    private Integer quantity;

    @NotNull(message = "The price is required.")
    @Positive(message = "The price must be a positive number.")
    private BigDecimal price;

    @NotNull(message = "The brand ID is required.")
    private Long brandId;

    /*@NotNull(message = "At least one category ID is required.")
    private List<Long> categoryIds;*/

}
