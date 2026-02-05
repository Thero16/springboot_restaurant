package com.nomolestar.mimido.ingredient.model;

import com.nomolestar.mimido.dish.model.Dish;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ingredients")
@Data
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String name;
    @Column(length = 100)
    private String category;
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @ManyToMany(mappedBy = "ingredients")
    private Set<Dish> dishes = new HashSet<>();

}
