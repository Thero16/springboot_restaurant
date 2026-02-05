package com.nomolestar.mimido.ingredient.mapper;

import com.nomolestar.mimido.chef.dto.ChefResponseDTO;
import com.nomolestar.mimido.chef.model.Chef;
import com.nomolestar.mimido.dish.dto.DishCreateDTO;
import com.nomolestar.mimido.dish.dto.DishUpdateDTO;
import com.nomolestar.mimido.dish.model.Dish;
import com.nomolestar.mimido.ingredient.dto.IngredientCreateDTO;
import com.nomolestar.mimido.ingredient.dto.IngredientResponseDTO;
import com.nomolestar.mimido.ingredient.dto.IngredientUpdateDTO;
import com.nomolestar.mimido.ingredient.model.Ingredient;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class IngredientMapper {
    private IngredientMapper () {}
    public static IngredientResponseDTO toIngredientResponseDTO(Ingredient ingredient) {
        Set<Long> dishesIds;
        if (ingredient.getDishes()!=null){
            dishesIds= ingredient.getDishes()
                    .stream()
                    .map(Dish::getId).collect(Collectors.toSet());
        } else {
            dishesIds = new HashSet<>();
        }
        return new IngredientResponseDTO(
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getCategory(),
                ingredient.getPrice(),
                dishesIds
        );
    }

    public static Ingredient toEntity(IngredientCreateDTO dto){
        Ingredient ingredient = new Ingredient();
        ingredient.setName(dto.name());
        ingredient.setCategory(dto.category());
        ingredient.setPrice(dto.price());
        return ingredient;
    }

    public static void updateEntity(IngredientUpdateDTO dto, Ingredient ingredient){
        ingredient.setName(dto.name());
        ingredient.setCategory(dto.category());
        ingredient.setPrice(dto.price());
    }
}
