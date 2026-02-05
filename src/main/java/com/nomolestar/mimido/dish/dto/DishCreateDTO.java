package com.nomolestar.mimido.dish.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.Set;

public record DishCreateDTO(
        @NotBlank(message = "Pone algo pedazo de mierda")
        @Size(min = 2, max = 140)
        String name,

        @NotBlank(message = "Pone el puto precio o es que acaso es gratis malparida")
        @DecimalMin(value = "0.00", inclusive = false, message = "Ponlo mayor a cero o sos maricon")
        @Digits(integer = 8, fraction = 2)
        BigDecimal price,

        @NotNull(message = "Ponle chef id")
        @Positive(message = "Pon el chef id positivo o acaso eres retrasado?")
        Long chefId,

        Set<@Positive Long>ingredientIds
) {
}
