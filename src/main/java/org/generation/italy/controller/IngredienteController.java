package org.generation.italy.controller;

import java.util.List;

import javax.validation.Valid;

import org.generation.italy.model.Ingrediente;
import org.generation.italy.model.Pizza;
import org.generation.italy.service.IngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/ingredienti")

public class IngredienteController {
	
	@Autowired
	private IngredienteService ingredienteService;
	
	
	@GetMapping
	public String list(Model model, @RequestParam(name="keyword", required=false) String keyword) {
		List<Ingrediente> result;
		if(keyword != null && !keyword.isEmpty()) {
			result = ingredienteService.findByKeyword(keyword);
			model.addAttribute("keyword", keyword);
		} else {
			result = ingredienteService.findAllSortedByName();
		}
		model.addAttribute("list2", result);
		return "/ingredienti/list2";
	}
	
	//edit - add?
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("edit", false);
		model.addAttribute("ingrediente", new Ingrediente());
		model.addAttribute("ingredienteList", ingredienteService.findAllSortedByName());
		return "/ingredienti/edit";
	}

	
	@PostMapping("/create")
	public String doCreate(@Valid @ModelAttribute("ingrediente") Ingrediente formIngrediente, BindingResult bindingResult, Model model) {

		if(bindingResult.hasErrors()) {
			model.addAttribute("edit", false);
			return "/ingredienti/edit";
		}
		ingredienteService.create(formIngrediente);
		return "redirect:/ingredienti";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("edit", true);
		model.addAttribute("ingrediente", ingredienteService.getById(id));
		return "/ingredienti/edit";
	}
	
	@PostMapping("/edit/{id}")
	public String doEdit (@Valid @ModelAttribute("ingrediente") Ingrediente formIngrediente, 
			BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("edit", true);
//			model.addAttribute("ingredienteList", ingredienteService.findAllSortedByName());
			return "/ingredienti/edit";
		}
		ingredienteService.create(formIngrediente);
		redirectAttributes.addFlashAttribute("successMessage", "Ingrediente modificato!");
		return "redirect:/ingredienti";
	}
	
	@GetMapping("/delete/{id}")
	public String doDelete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		if(ingredienteService.getById(id) == null) {
		}
		ingredienteService.deleteById(id);
		redirectAttributes.addFlashAttribute("successMessage", "Ingrediente eliminato!");
		return "redirect:/ingredienti";
	}




	

}
