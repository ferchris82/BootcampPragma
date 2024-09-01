package com.chrisferdev.hus.domain.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Brand {
    private Long id;
    @NotBlank(message = "El nombre de la marca es obligatorio")
    @Size(max = 50, message = "El nombre de la marca no puede exceder los 50 caracteres")
    private String name;
    @NotBlank(message = "La descripción de la categoría es obligatorio")
    @Size(max = 120, message = "La descripción de la categoría no puede exceder los 120 caracteres")
    private String description;
}
