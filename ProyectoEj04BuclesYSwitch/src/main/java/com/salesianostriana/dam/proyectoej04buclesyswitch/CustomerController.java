package com.salesianostriana.dam.proyectoej04buclesyswitch;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import lombok.RequiredArgsConstructor;

@Controller @RequiredArgsConstructor
public class CustomerController {	
	/*
	 * De nuevo, necesitamos un objeto del tipo 
	 * CustomerService para poder llamar
	 * a los métodos de dicha clase, 
	 * por lo que lo inyectamos aquí. Explicamos la inyección:
	 * 
	 * 1. Con autowired en el atributo (no lo hacemso así)
	 * 2. No ponemos el autowired al atributo,
	 * en este caso service, y debemos tener un constructor donde
	 * se asigne ese atributo, es decir, como este:
	 * 
	public CustomerController(CustomerService service) {
		super();
		this.service = service;
	}
	
	Como Lombok es nuestro amigo, si usamos sus anotaciones, 
	no necesitamos escribir el código del constructor, 
	basta con poner la anotación @RequiredArgsConstructor y
	OJO, el atributo como final.
	 * */
	
	private final CustomerService service;
	
	//Nuevamente anotamos con el tipo de petición que atenderá este método (tipo get) 
	//cuando se escriba la ruta localhost:9000/listGen
	
	@GetMapping("/listGen")
	public String listarVarios (Model model) {
			
		/*
		 * Agregamos la lista al modelo usando el método del servicio
		 * y cuidando el nombre de la lista que usaremos en el html
		*/
		
		model.addAttribute("customerList", service.getLista());		
		return "PlantillaSwitch";
	}
	
	@GetMapping("/listGen2")
	public String listarVarios2 (Model model) {
			
		/*
		 * Agregamos la lista al modelo usando el método del servicio
		 * y cuidando el nombre de la lista que usaremos en el html
		*/
		
		model.addAttribute("customerList", service.getLista());		
		return "PlantillaSwitch2";
	}
	
	@GetMapping("/listGen3")
	public String listarVarios3 (Model model) {
			
		/*
		 * Agregamos la lista al modelo usando el método del servicio
		 * y cuidando el nombre de la lista que usaremos en el html
		*/
		
		model.addAttribute("customerList", service.getLista());		
		return "PlantillaSwitch3";
	}
	
}