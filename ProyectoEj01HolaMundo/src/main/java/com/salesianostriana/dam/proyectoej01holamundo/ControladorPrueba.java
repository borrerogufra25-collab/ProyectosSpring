package com.salesianostriana.dam.proyectoej01holamundo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorPrueba {

	@GetMapping("/prueba1")
	public String pruebaUno(Model model) {

		model.addAttribute("nombre", new Persona("Paco", "Mermela"));
		model.addAttribute("mensaje", "Un texto de prueba");

		return "EsUnaPrueba";

	}

}
