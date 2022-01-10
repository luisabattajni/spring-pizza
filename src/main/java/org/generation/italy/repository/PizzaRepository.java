package org.generation.italy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.generation.italy.model.Pizza;

import org.springframework.stereotype.Repository;

@Repository

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {

}
