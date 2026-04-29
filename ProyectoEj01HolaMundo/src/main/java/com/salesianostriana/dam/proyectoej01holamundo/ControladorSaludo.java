package com.salesianostriana.dam.proyectoej01holamundo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//Un controller es el que recibe una petición,
//hace algo o se lo manda al que sea, y luego lo manda a pintar 
@Controller
public class ControladorSaludo {

	@GetMapping("/saludo3")
	public String welcome3(Model model) {
		// El primero que va entre "" es el nombre de la variable (Que luego se asigna
		// en el html) y lo segundo es el contenido

		model.addAttribute("saludo", "¡Hola mundo!");
		model.addAttribute("mensaje", "¡Se me está haciendo eterno el proyecto final!");
		model.addAttribute("url", "https://media1.tenor.com/m/GZyo92WEdcQAAAAC/duck-ducks.gif");

		return "SaludoYEnlace"; // En el return escribimos el nombre del archivo html sin la extensión
	}

	@GetMapping("/saludo2")
	public String welcome2(Model model) {

		model.addAttribute("persona", new Persona("Fran", "Borrero Guerrero"));

		return "SaludoPersonalizado";

	}

}
