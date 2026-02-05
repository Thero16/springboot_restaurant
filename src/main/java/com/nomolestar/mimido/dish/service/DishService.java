package com.nomolestar.mimido.dish.service;

import com.nomolestar.mimido.chef.model.Chef;
import com.nomolestar.mimido.chef.service.ChefService;
import com.nomolestar.mimido.common.exception.NotFound;
import com.nomolestar.mimido.dish.dto.DishCreateDTO;
import com.nomolestar.mimido.dish.dto.DishResponseDTO;
import com.nomolestar.mimido.dish.dto.DishUpdateDTO;
import com.nomolestar.mimido.dish.mapper.DishMapper;
import com.nomolestar.mimido.dish.model.Dish;
import com.nomolestar.mimido.dish.repository.DishRepository;
import com.nomolestar.mimido.ingredient.model.Ingredient;
import com.nomolestar.mimido.ingredient.repository.IngredientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DishService {
    private final DishRepository dishRepository;
    private final ChefService chefService;
    private final IngredientRepository ingredientRepository;
    public DishService(DishRepository dishRepository, ChefService chefService,  IngredientRepository ingredientRepository) {
        this.dishRepository = dishRepository;
        this.chefService = chefService;
        this.ingredientRepository= ingredientRepository;
    }

    public DishResponseDTO create(DishCreateDTO dto){
        Chef chef= chefService.getEntity(dto.chefId());
        Dish dish= DishMapper.toEntity(dto, chef);
        if (dto.ingredientIds() != null && !dto.ingredientIds().isEmpty()) {

            Set<Ingredient> ingredients = new HashSet<>(
                    ingredientRepository.findAllById(dto.ingredientIds())
            );

            dish.setIngredients(ingredients);
        }

        dishRepository.save(dish);
        return DishMapper.toResponseDTO(dish);
    }
    public DishResponseDTO findById(Long id){
        Dish dish= dishRepository.findById(id).
                orElseThrow(()-> new NotFound("Dish not found"));
        return DishMapper.toResponseDTO(dish);
    }
    public Page<DishResponseDTO> findAll(Pageable pageable){
        return dishRepository.findAll(pageable).map(DishMapper::toResponseDTO);
    }

    public List<DishResponseDTO> findByChef(Long chefId){
        // Validar que el chef exista
        List<Dish> dishes= dishRepository.findByChefId(chefId);
        return dishes.stream().map(DishMapper::toResponseDTO).
                toList();
    }
    public DishResponseDTO updateById(Long id, DishUpdateDTO dto){
        Dish dish= dishRepository.findById(id).
                orElseThrow(()-> new NotFound("Dish not found"));
        if (dto.ingredientIds() != null && !dto.ingredientIds().isEmpty()) {

            Set<Ingredient> ingredients = new HashSet<>(
                    ingredientRepository.findAllById(dto.ingredientIds())
            );

            dish.setIngredients(ingredients);
        }
        Chef chef= chefService.getEntity(dto.chefId());
        dish.setChef(chef);
        dish.setName(dto.name());
        dish.setPrice(dto.price());
        Dish saved = dishRepository.save(dish);
        return DishMapper.toResponseDTO(saved);
    }
    public void deleteById(Long id){
        Dish dish= dishRepository.findById(id).orElseThrow(()-> new NotFound("Dish not found"));
        dishRepository.delete(dish);
    }

}
