package com.salesianostriana.dam.proyectoej07bfechasconbd.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.proyectoej07bfechasconbd.model.Alumno;
import com.salesianostriana.dam.proyectoej07bfechasconbd.servicios.AlumnoServicio;

@Controller
public class AlumnoController {

	/*
	 * Como ya se ha explicado otras veces, hay 3 formas de inyectar un objeto:
	 * - Con @Autowired en el atributo
	 * - Haciendo el atributo final y con el constructor
	 * - Con Setters (no la usaremos)
	 * Aquí vemos la segunda que es la mejor ahora mismo 
	 * (en algunos ejemplos anteriores hemos usado @Autowired en el atributo)
	 * */
	private AlumnoServicio alumnoServicio;

	public AlumnoController(AlumnoServicio servicio) {
		alumnoServicio = servicio;
	}

	// Controller para mostrar la página list con la lista de alumnos
	// Inicialmente no muestra ninguno
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("alumnos", alumnoServicio.findAll());
		return "list";
	}

	// En el html list, podemos ver el th:href que nos lleva hasta este controller
	// anotado con /nuevo que es parte del proceso de agregar nuevo alumno y 
	// esta petición get primera para el formulario vacío y que se gestionará
	// en el último método, procesaFormulario anotado con el @PostMapping
	@GetMapping("/nuevo")
	public String muestraFormulario(Model model) {
		model.addAttribute("alumno", new Alumno());
		return "form";
	}

	/*
	 * Debemos ir acostumbrándonos a que, como vamos a tener muchos
	 * controller, estos irán teniendo rutas complicadas en los Mapping
	 * por eso, iremos agregando rutas más complejas, por ejemplo, 
	 * en este caso, como en ambos casos es para agregar un nuevo alumno
	 * ponemos en la ruta /nuevo
	 * Veremos formas de ahorrar esta escritura de rutas cuando se hagan muy largas
	 * 
	 * Redirect: Significa que al acabar el proceso del controller, no lleva a una
	 * página html sino que nos lleva a otro controller, en este caso, de nuevo al
	 * controller que pinta la lista de alumnos, el método controller que tiene en
	 * el GetMapping la barra. Esto se hace así para "actualizar" la lista con el
	 * nuevo alumno insertado después de procesar el formulario
	 */
	@PostMapping("/nuevo/submit")
	public String procesaFormulario(@ModelAttribute("alumno") Alumno alumno) {
		/*
		 * Como fecha de último acceso no es un campo del formulario, 
		 * estamos creándola
		 * aquí para poder tenerla en el alumno en este ejemplo
		 */
		alumno.setFechaUltimoAcceso(LocalDateTime.now());
		alumnoServicio.save(alumno);

		return "redirect:/";
	}
	
	//Para la parte del cumpleaños, mejor pasar dos listas al controller para no tener que trabajar
	//las comprobaciones de fechas en el html
	//recuerda que debes escribir listar en el navegador para ver la lista
	//que se pinta diferente con cumpleaños, es decir, el html list2
	
    
    @GetMapping("/listar")
    public String listarAlumnos(Model model) {
        // 1. Obtenemos todos los alumnos
        List<Alumno> todos = alumnoServicio.findAll();
        
        // 2. Obtenemos solo los que cumplen años hoy (usando el método del Repositorio que vimos)
        List<Alumno> cumpleaneros = alumnoServicio.obtenerCumpleanerosHoy();
        
        model.addAttribute("alumnos", todos);
        model.addAttribute("cumpleaneros", cumpleaneros); // Esta es la clave para el HTML
        
        return "list2";
    }

}