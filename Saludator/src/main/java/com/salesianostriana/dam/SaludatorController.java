package com.salesianostriana.dam;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SaludatorController {

	
	private final Saludator saludator;
	
	/*public SaludatorController(
			//@Qualifier("spanishSaludator")
			Saludator saludator) {
		this.saludator = saludator;
	}*/
	
	@GetMapping("/")
	public String defaultGreeting(Model model) {
		model.addAttribute("saludo", saludator.saludar());
		return "index";
	}
	
	@GetMapping("/message/")
	public String msgGreeting(Model model,
			@RequestParam String msg) {
		model.addAttribute("saludo", saludator.saludar(msg));
		return "index";
	}
	
	
}
