package org.generation.italy.service;



import java.util.List;


//import org.generation.italy.model.Ingrediente;
import org.generation.italy.model.Pizza;
import org.generation.italy.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;



@Service

public class PizzaService {
	
	@Autowired
	private PizzaRepository repository;
	
	public List<Pizza> findAllSortedByName(){
		return repository.findAll(Sort.by("name"));
	} 
	
	public List<Pizza> findByKeyword(String keyword){
		return repository.findByNameContainingIgnoreCaseOrderByName(keyword);
	}
	
//	public Pizza save(Pizza pizza) {
//		//pizza.setCreatedAt(LocalDateTime.now());
//		return repository.save(pizza);
//	}
	
	public Pizza create(Pizza pizza) {
		//pizza.setCreatedAt(LocalDateTime.now());
		return repository.save(pizza);
	}
	
	public Pizza getById(Integer id) {
		return repository.getById(id);
	}
	
	public Pizza update(Pizza pizza) {
//		LocalDateTime createdAt = repository.getById(book.getId()).getCreatedAt();
//		book.setCreatedAt(createdAt);
		return repository.save(pizza);
	}
	
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}
	
	
	

}
