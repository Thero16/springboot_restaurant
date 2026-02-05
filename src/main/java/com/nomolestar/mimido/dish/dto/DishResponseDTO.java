package com.nomolestar.mimido.dish.dto;

import java.math.BigDecimal;

public record DishResponseDTO(
        Long id,
        String name,
        BigDecimal price,
        Long chefId,
        String chefName
) {
}
