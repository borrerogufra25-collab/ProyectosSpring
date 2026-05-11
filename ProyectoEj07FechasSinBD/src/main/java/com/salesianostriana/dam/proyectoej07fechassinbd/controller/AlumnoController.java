package com.salesianostriana.dam.proyectoej07fechassinbd.controller;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.proyectoej07fechassinbd.model.Alumno;
import com.salesianostriana.dam.proyectoej07fechassinbd.service.AlumnoServicio;

@Controller
public class AlumnoController {

	/*
	 * Como habéis visto con Luismi, hay 3 formas de inyectar un objeto: 
	 * - Con @Autowired en el atributo 
	 * - Haciendo el atributo private y final y con el constructor que asigna la inyección que queremos. 
	 * En este caso pondríamos @Autowired en el constructor pero desde
	 * la versión 4.3 de Spring si la clase solo tiene un constructor, @Autowired es opcional 
	 * y puede omitirse. (Se recomienda declarar los campos
	 * como final para la inmutabilidad) 
	 * - Con Setters (no la usaremos) 
	 * Ya hemos dicho que la mejor es la segunda y es la que nosotros usaremos.
	 */
	
	private final AlumnoServicio alumnoServicio;

	public AlumnoController(AlumnoServicio servicio) {
		this.alumnoServicio = servicio;
	}

	// Controller para mostrar la página list con la lista de alumnos
	// Inicialmente no muestra ninguno, porque está vacía
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("alumnos", alumnoServicio.getLista());
		/*
		 * Agregamos un syso para ver en la consola cómo se imprime la fecha con el formato del
		 * pattern dado en la clase POJO @DateTimeFormat(pattern = "yyyy-MM-dd") 
		 * Así, vemos la diferencia entre cómo se guarda (con el patrón), cómo se muestra en th,
		 * y cómo sin pattern
		 */

		System.out.println(alumnoServicio.getLista());
		return "list";
	}

	// En el html list, podemos ver el th:href que nos lleva hasta este controller
	// que muestra primero con este método el formulario vacío que se gestionará
	// en el último método, procesaFormulario
	
	@GetMapping("/nuevo")
	public String muestraFormulario(Model model) {
		model.addAttribute("alumno", new Alumno());
		return "form";
	}

	/*
	 * Debemos ir acostumbrándonos a que, como vamos a tener muchos controller,
	 * estos irán teniendo rutas más complicadas en los Get y Post Mapping por eso, 
	 * por ejemplo, en este caso, como en ambos casos
	 * es para agregar un nuevo alumno ponemos en la ruta /nuevo. Veremos formas de
	 * ahorrar esta escritura de rutas cuando se hagan demasiado largas
	 * 
	 * Redirect: IMPORTANTE: Significa que al acabar el proceso del controller, 
	 * no lleva a una página html como hasta ahora sino que nos lleva a otro controller, 
	 * en este caso, de nuevo al controller que pinta la lista de alumnos, 
	 * esto es, el método controller que tiene enel GetMapping la barra. 
	 * Se hace así para "actualizar" la lista con el
	 * nuevo alumno insertado después de procesar el formulario
	 */
	
	@PostMapping("/nuevo/submit")
	public String procesaFormulario(@ModelAttribute("alumno") Alumno alumno) {
		/*
		 * Como fecha de último acceso no es un campo del formulario, estamos creándola
		 * aquí para poder tenerla en el alumno en este ejemplo
		 */
		alumno.setFechaUltimoAcceso(LocalDateTime.now());
		alumnoServicio.agregar(alumno);

		return "redirect:/";
	}

}
