package com.nomolestar.mimido.ingredient.repository;

import com.nomolestar.mimido.ingredient.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

}
