package org.generation.italy.service;

import java.util.List;

import org.generation.italy.model.Ingrediente;
import org.generation.italy.repository.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service

public class IngredienteService {
	
	@Autowired
	private IngredienteRepository repository;
	
	public List<Ingrediente> findAllSortByName(){
		return repository.findAll(Sort.by("name"));
		
	}


}
