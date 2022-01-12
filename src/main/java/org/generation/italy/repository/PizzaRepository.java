package org.generation.italy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import org.generation.italy.model.Pizza;

import org.springframework.stereotype.Repository;

@Repository

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {

	List<Pizza> findByNameContainingIgnoreCaseOrderByName(String keyword);

}
