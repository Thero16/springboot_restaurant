package com.nomolestar.mimido.dish.controller;

import com.nomolestar.mimido.dish.dto.DishCreateDTO;
import com.nomolestar.mimido.dish.dto.DishResponseDTO;
import com.nomolestar.mimido.dish.dto.DishUpdateDTO;
import com.nomolestar.mimido.dish.model.Dish;
import com.nomolestar.mimido.dish.service.DishService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/dishes")
//Consultar como paginar, consultar y hacer la parte de ingredients
public class DishController {
    private DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DishResponseDTO postEntity(@Valid @RequestBody DishCreateDTO dish) {
        return dishService.create(dish);
    }
    @GetMapping
    public Page<DishResponseDTO> findAll(Pageable pageable) {
        return dishService.findAll(pageable);
    }
    @GetMapping("/{id}")
    public DishResponseDTO findById(@PathVariable Long id) {
        return dishService.findById(id);
    }
    @GetMapping("/by-chef/{chefId}")
    public List<DishResponseDTO> findByChefId(@PathVariable Long chefId) {
        return dishService.findByChef(chefId);
    }
    @PutMapping("/{id}")
    public DishResponseDTO updateEntity(@PathVariable Long id, @Valid @RequestBody DishUpdateDTO dish) {
        return dishService.updateById(id, dish);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEntity(@PathVariable Long id) {
        dishService.deleteById(id);
    }
}
