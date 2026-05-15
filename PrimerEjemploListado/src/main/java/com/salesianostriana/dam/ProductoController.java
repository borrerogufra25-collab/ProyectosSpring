package com.salesianostriana.dam;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/producto/")
@RequiredArgsConstructor
public class ProductoController {
	
	private final ProductoRepository repository;
	
	@GetMapping
	public String list(Model model) {
		model.addAttribute("productos", repository.getAll());
		return "index";
	}
	

}
