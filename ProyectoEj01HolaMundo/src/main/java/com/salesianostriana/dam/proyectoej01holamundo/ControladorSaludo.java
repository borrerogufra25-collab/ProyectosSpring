package com.salesianostriana.dam.proyectoej01holamundo;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

		model.addAttribute("persona", new Persona("Fran", "Borrero Guerrero", 0, null));

		return "SaludoPersonalizado";

	}

	@GetMapping({ "/", "welcome" }) // Esto sirve para hacer que con el mismo controller se pueda llamar dos veces
	public String welcome(@RequestParam(name = "nombre", required = false, defaultValue = "Mundo") String nombre,
			Model model) {

		model.addAttribute("nombre", nombre);
		return "index";

	}

	public String welcomePotente(Model model) {
		model.addAttribute("personaPotente", new Persona("Fran", "Borrero", 123.534252345, LocalDate.of(1998, 05, 11)));
		return "Saludo tocho";
	}

}
