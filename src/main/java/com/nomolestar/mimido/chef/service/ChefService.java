package com.nomolestar.mimido.chef.service;

import com.nomolestar.mimido.chef.dto.ChefCreateDTO;
import com.nomolestar.mimido.chef.dto.ChefResponseDTO;
import com.nomolestar.mimido.chef.dto.ChefUpdateDTO;
import com.nomolestar.mimido.chef.mapper.ChefMapper;
import com.nomolestar.mimido.chef.model.Chef;
import com.nomolestar.mimido.chef.repository.ChefRepository;
import com.nomolestar.mimido.common.exception.NotFound;
import jakarta.validation.Valid;
import org.aspectj.weaver.ast.Not;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ChefService {
    private final ChefRepository chefRepository;
    public ChefService(ChefRepository chefRepository) {
        this.chefRepository = chefRepository;
    }
    public ChefResponseDTO create(ChefCreateDTO dto) {
        Chef chef= ChefMapper.toEntity(dto);
        Chef saved= chefRepository.save(chef);
        return ChefMapper.toChefResponseDTO(saved);
    }
    public Page<ChefResponseDTO> findAll(Pageable pageable) {
        return chefRepository.findAll(pageable)
                .map(ChefMapper::toChefResponseDTO);
    }
    public ChefResponseDTO findById(Long id) {
        Chef chef = chefRepository.findById(id)
                .orElseThrow(()->new NotFound("Chef not found"));
        return ChefMapper.toChefResponseDTO(chef);
    }
    public void  deleteById(Long id) {
        Chef chef= chefRepository.findById(id)
                .orElseThrow(()->new NotFound("Chef not found"));
        chefRepository.delete(chef);
    }
    public ChefResponseDTO update(Long id, ChefUpdateDTO dto) {
        Chef chef= chefRepository.findById(id).
                orElseThrow(()->new NotFound("Chef not found"));
        ChefMapper.updateEntity(chef, dto);
        Chef saved= chefRepository.save(chef);
        return ChefMapper.toChefResponseDTO(saved);
    }

    public Chef getEntity(Long id) {
        return chefRepository.findById(id)
                .orElseThrow(()->new NotFound("Chef not found"));
    }
}
