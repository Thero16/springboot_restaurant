package com.nomolestar.mimido.ingredient.controller;

import com.nomolestar.mimido.ingredient.dto.IngredientCreateDTO;
import com.nomolestar.mimido.ingredient.dto.IngredientResponseDTO;
import com.nomolestar.mimido.ingredient.dto.IngredientUpdateDTO;
import com.nomolestar.mimido.ingredient.model.Ingredient;
import com.nomolestar.mimido.ingredient.service.IngredientService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/ingredients")
public class IngredientController {
    private IngredientService ingredientService;
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }
    @GetMapping
    public Page<IngredientResponseDTO> findAll(Pageable pageable){
        return ingredientService.findAll(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IngredientResponseDTO postEntity (@Valid @RequestBody IngredientCreateDTO ingredient){
        return ingredientService.create(ingredient);
    }

    @GetMapping("/{id}")
    public IngredientResponseDTO findById(@PathVariable Long id){
        return ingredientService.findById(id);
    }

    @PutMapping
    public IngredientResponseDTO updateEntity(@PathVariable Long id, @Valid @RequestBody IngredientUpdateDTO ingredient){
        return ingredientService.updateById(id, ingredient);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEntity(@PathVariable Long id){
        ingredientService.deleteById(id);
    }
}
