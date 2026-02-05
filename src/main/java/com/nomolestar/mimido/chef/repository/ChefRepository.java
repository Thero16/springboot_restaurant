package com.nomolestar.mimido.chef.repository;

import com.nomolestar.mimido.chef.model.Chef;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChefRepository extends JpaRepository<Chef, Long> {
}
