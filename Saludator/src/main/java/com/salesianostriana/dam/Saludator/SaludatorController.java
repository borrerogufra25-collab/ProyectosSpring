package com.salesianostriana.dam.Saludator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SaludatorController {

	private Saludator saludator;

	public SaludatorController(Saludator saludator) {
		this.saludator = saludator;
	}
	
	@GetMapping("/")
	public String defaultGreeting(Model model) {
		
		model.addAttribute("Saludo", saludator.saludar());
		return "index";
	}

}
