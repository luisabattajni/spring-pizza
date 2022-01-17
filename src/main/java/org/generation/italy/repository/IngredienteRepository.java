package org.generation.italy.repository;

import java.util.List;

import org.generation.italy.model.Ingrediente;
//import org.generation.italy.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface IngredienteRepository extends JpaRepository<Ingrediente, Integer> {
	
	List<Ingrediente> findByNameContainingIgnoreCaseOrderByName(String keyword);

}
