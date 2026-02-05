package com.nomolestar.mimido.dish.repository;

import com.nomolestar.mimido.dish.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Long> {
    List<Dish> findByChefId(Long chefId);
    //@Query("Select * from .....")
}
