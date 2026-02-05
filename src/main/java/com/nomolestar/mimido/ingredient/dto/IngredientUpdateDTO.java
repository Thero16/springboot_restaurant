package com.nomolestar.mimido.ingredient.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record IngredientUpdateDTO(
        @NotBlank(message = "Pone algo pedazo de mierda")
        @Size(min = 2, max = 140)
        String name,

        @NotBlank(message = "Pon la malparida category pedazo de aborto de teletubi")
        @Size(min = 2, max = 140)
        String category,

        @NotBlank(message = "Pone el puto precio o es que acaso es gratis malparida")
        @DecimalMin(value = "0.00", inclusive = false, message = "Ponlo mayor a cero o sos maricon")
        @Digits(integer = 8, fraction = 2)
        BigDecimal price
) {
}
