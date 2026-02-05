package com.nomolestar.mimido.chef.mapper;

import com.nomolestar.mimido.chef.dto.ChefCreateDTO;
import com.nomolestar.mimido.chef.dto.ChefResponseDTO;
import com.nomolestar.mimido.chef.dto.ChefUpdateDTO;
import com.nomolestar.mimido.chef.model.Chef;
import com.nomolestar.mimido.dish.model.Dish;

import java.util.ArrayList;
import java.util.List;

public class ChefMapper {
    private ChefMapper() {}
    public static ChefResponseDTO toChefResponseDTO(Chef chef) {
        List<Long> dishesIds;
        if (chef.getDishes()==null){
            dishesIds= new ArrayList<>();
        } else {
            dishesIds= chef.getDishes()
                    .stream()
                    .map(Dish::getId).toList();
        }
        return new ChefResponseDTO(
                chef.getId(),
                chef.getSpeciality(),
                chef.getName(),
                chef.getDescription(),
                dishesIds
        );
    }

    public static Chef toEntity(ChefCreateDTO dto){
        Chef chef = new Chef();
        chef.setName((dto.name()));
        chef.setDescription((dto.description()));
        chef.setSpeciality(dto.speciality());
        return chef;
    }

    public static void updateEntity(Chef chef, ChefUpdateDTO dto){
        chef.setName(dto.name());
        chef.setDescription(dto.description());
        chef.setSpeciality(dto.speciality());
    }
}
