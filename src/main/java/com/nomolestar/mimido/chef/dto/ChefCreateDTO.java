package com.nomolestar.mimido.chef.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ChefCreateDTO(
        @NotBlank(message = "name is required")
        @Size(min=2, max=120, message = "name must be between 2 and 120")
        String name,
        @Size(max=80, message = "speciality must be at most 80 characters")
        String speciality,
        @Size(max=100)
        String description
) {
}
