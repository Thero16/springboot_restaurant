package com.nomolestar.mimido.ingredient.dto;

import java.math.BigDecimal;
import java.util.Set;

public record IngredientResponseDTO(
        Long id,
        String name,
        String category,
        BigDecimal price,
        Set<Long> dishIds
) {
}
