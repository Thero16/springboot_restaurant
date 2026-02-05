package com.nomolestar.mimido.chef.dto;

import java.util.List;

public record ChefResponseDTO(
        Long id,
        String speciality,
        String name,
        String description,
        List<Long> dishIds
) {
}
