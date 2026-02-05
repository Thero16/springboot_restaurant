package com.nomolestar.mimido.chef.controller;

import com.nomolestar.mimido.chef.dto.ChefCreateDTO;
import com.nomolestar.mimido.chef.dto.ChefResponseDTO;
import com.nomolestar.mimido.chef.dto.ChefUpdateDTO;
import com.nomolestar.mimido.chef.repository.ChefRepository;
import com.nomolestar.mimido.chef.service.ChefService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chefs")
public class ChefController {
    private final ChefService chefService;
    private final ChefRepository chefRepository;

    public ChefController(ChefService chefService, ChefRepository chefRepository) {
        this.chefService = chefService;
        this.chefRepository = chefRepository;
    }

    @GetMapping
    public Page<ChefResponseDTO> getChefs(Pageable pageable) {
        return chefService.findAll(pageable);
    }
    @GetMapping("/{id}")
    public ChefResponseDTO getChef(@PathVariable Long id) {
        return chefService.findById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ChefResponseDTO create(@Valid @RequestBody ChefCreateDTO dto){
        return chefService.create(dto);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        chefService.deleteById(id);
    }
    @PutMapping("/{id}")
    //Put para actualizar el registro
    //Patch para alguna parte del registro
    public ChefResponseDTO update(@PathVariable Long id, @Valid @RequestBody ChefUpdateDTO dto){
        return chefService.update(id, dto);
    }

}
