package com.nomolestar.mimido.chef.model;

import com.nomolestar.mimido.dish.model.Dish;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "chefs")
@Data
public class Chef {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false, length= 80)
    private String speciality;

    @OneToMany(mappedBy = "chef", cascade = CascadeType.ALL)
    private List<Dish> dishes;
}
