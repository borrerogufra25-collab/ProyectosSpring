package com.salesianostriana.dam.PrimerEjemploCompleto;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class Controlador {

	@GetMapping("/")
	public String principal() {
		return "principal";
	}

}
