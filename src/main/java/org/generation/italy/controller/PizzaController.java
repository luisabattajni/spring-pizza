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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
			result = service.findByKeyword(keyword);
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
		model.addAttribute("edit", false);
		model.addAttribute("pizza", new Pizza());
		model.addAttribute("ingredienteList", ingredienteService.findAllSortedByName());
		return "/pizze/add";
	}

	
	@PostMapping("/create")
	public String doCreate(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {

		// se ci sono errori torni alla form
		if(bindingResult.hasErrors()) {
			model.addAttribute("edit", false);
			model.addAttribute("ingredienteList", ingredienteService.findAllSortedByName());
			return "/pizze/add";
		}
		// se non ci sono errori salva i dati e torna alla lista
		service.create(formPizza);
		return "redirect:/pizze";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("edit", true);
		model.addAttribute("pizza", service.getById(id));
		model.addAttribute("ingredienteList", ingredienteService.findAllSortedByName());
		return "/pizze/add";
	}
	
	@PostMapping("/edit/{id}")
	public String doEdit(@Valid @ModelAttribute("pizza") Pizza formPizza, 
			BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("edit", true);
			model.addAttribute("ingredienteList", ingredienteService.findAllSortedByName());
			return "/pizze/add";
		}
		service.update(formPizza);
		redirectAttributes.addFlashAttribute("successMessage", "Pizza edited!");
		return "redirect:/pizze";
	}
	
	@GetMapping("/delete/{id}")
	public String doDelete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		if(service.getById(id) == null) {
			// ritorno error message
		}
		service.deleteById(id);
		redirectAttributes.addFlashAttribute("successMessage", "Pizza eliminata!");
		return "redirect:/pizze";
	}


}
