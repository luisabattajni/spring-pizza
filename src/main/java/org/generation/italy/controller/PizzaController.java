package org.generation.italy.controller;



import java.util.List;

import javax.validation.Valid;

import org.generation.italy.model.Pizza;
import org.generation.italy.service.IngredienteService;
import org.generation.italy.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/pizze")
public class PizzaController {
	
	@Autowired
	private PizzaService service;
	
	@Autowired
	private IngredienteService ingredienteService;
	
	@GetMapping
	public String list(Model model, @RequestParam(name="keyword", required=false) String keyword) {
		List<Pizza> result;
		if(keyword != null && !keyword.isEmpty()) {
			result = service.findByKeywordSortedByName(keyword);
			model.addAttribute("keyword", keyword);
		} else {
			result = service.findAllSortedByName();
		}
		model.addAttribute("list", result);
		return "/pizze/list";
	}
	
	//edit - add?
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("add", false);
		model.addAttribute("pizza", new Pizza());
		model.addAttribute("ingredienteList", ingredienteService.findAllSortByName());
		return "/pizze/add";
	}

	
	@PostMapping("/create")
	public String doCreate(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {

		// se ci sono errori torni alla form
		//attenzione all'html che ho chiamato add
		if(bindingResult.hasErrors()) {
			model.addAttribute("add", false);
			model.addAttribute("ingredienteList", ingredienteService.findAllSortByName());
			return "/pizze/add";
		}
		// se non ci sono errori salva i dati e torna alla lista
		service.create(formPizza);
		return "redirect:/pizze";
	}
	


}
