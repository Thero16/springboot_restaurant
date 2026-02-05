package com.nomolestar.mimido.ingredient.service;

import com.nomolestar.mimido.common.exception.NotFound;
import com.nomolestar.mimido.ingredient.dto.IngredientCreateDTO;
import com.nomolestar.mimido.ingredient.dto.IngredientResponseDTO;
import com.nomolestar.mimido.ingredient.dto.IngredientUpdateDTO;
import com.nomolestar.mimido.ingredient.mapper.IngredientMapper;
import com.nomolestar.mimido.ingredient.model.Ingredient;
import com.nomolestar.mimido.ingredient.repository.IngredientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {
    private final IngredientRepository ingredientRepository;
    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public IngredientResponseDTO create (IngredientCreateDTO dto) {
        Ingredient ingredient = IngredientMapper.toEntity(dto);
        ingredientRepository.save(ingredient);
        return IngredientMapper.toIngredientResponseDTO(ingredient);
    }

    public IngredientResponseDTO findById(long id){
        Ingredient ingredient = ingredientRepository.findById(id).
                orElseThrow(()-> new NotFound("Ingredient not found"));
        return IngredientMapper.toIngredientResponseDTO(ingredient);
    }

    public Page<IngredientResponseDTO> findAll(Pageable pageable){
       return ingredientRepository.findAll(pageable).map(IngredientMapper::toIngredientResponseDTO);
    }

    public IngredientResponseDTO updateById(long id, IngredientUpdateDTO dto) {
        Ingredient ingredient= ingredientRepository.findById(id).
                orElseThrow(()-> new NotFound("Ingredient not found"));
        ingredient.setName(dto.name());
        ingredient.setCategory(dto.category());
        ingredient.setPrice(dto.price());
        Ingredient saved= ingredientRepository.save(ingredient);
        return IngredientMapper.toIngredientResponseDTO(saved);
    }

    public void deleteById(long id){
        Ingredient ingredient= ingredientRepository.findById(id).
                orElseThrow(()-> new NotFound("Ingredient not found"));
        ingredientRepository.delete(ingredient);
    }
}
