package com.salesianostriana.dam.proyectoej08crudconbd.controladores;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.proyectoej08crudconbd.modelo.Alumno;
import com.salesianostriana.dam.proyectoej08crudconbd.service.AlumnoServicio;

@Controller
public class AlumnoControlador {
	
	/** Necesitaremos llamar a los métodos del servicio, por lo que inyectamos 
	 * aquí un objeto del tipo AlumnoServicio con el que poder llamar
	 * a métodos de dicho servicio, en este caso, como solo es un envoltorio
	 * del repositorio solo serán cruds, pero podría ser cualquier otro
	 * 
	 * Lo inyectamos mediante la forma atributo private y final y constructor
	 * */
	
	private AlumnoServicio alumnoServicio;
	
	public AlumnoControlador(AlumnoServicio servicio) {
		this.alumnoServicio = servicio;
	}
	
	/**
	 * Método que gestiona la petición "/" o de listado de alumnos "list", 
	 * ya que en ambas se mostrará la lista completa de alumnos.
	 * Para ello, como puede verse, se añaden al GetMapping los dos nombres de las rutas, 
	 * entre comillas dobles separados por coma.
	 */
	
	@GetMapping({"/", "/list"})
	public String listarTodos(Model model) {
		model.addAttribute("lista", alumnoServicio.findAll());
		return "index";
	}
	
	/**
	 * Método que atiende la petición de mostrar formulario, en este caso vacío. 
	 * Pasamos al model un nuevo alumno vacío para pintar el formulario.
	 */
	@GetMapping("/nuevo")
	public String mostrarFormulario(Model model) {
		model.addAttribute("alumno", new Alumno());
		return "formulario";
	}
	
	/**
	 * Método que procesa la petición post del formulario.
	 * Nombramos "la petición" en el PostMapping con la ruta /nuevo/submit 
	 * para distinguirla de la de editar que será, 
	 * como puede verse más abajo, /editar/submit
	 * 
	 * Por otro lado, no mandamos directamente a una web en el return, ya que esto 
	 * no nos "pintaría-actualizaría" la vista de la tabla con el nuevo elemento
	 * sino que devolvemos o redirigimos de nuevo al controller inicial (listarTodos) 
	 * para que pinte la tabla con el nuevo alumno recién agregado.
	 * Se hace usando redirect (que significa o se usa para mandar a otro controller
	 * en lugar de a una página html y después de los : 
	 * la ruta del método controller, en este caso /.
	 */
	@PostMapping("/nuevo/submit")
	public String procesarFormulario(@ModelAttribute("alumno") Alumno a) {
		alumnoServicio.save(a);
		return "redirect:/";//Podría ser también return "redirect:/list
	}
		
	/**
	 * Método que atiende la petición de mostrar el formulario de edición de un alumno, 
	 * con los datos del alumno a modificar ya cargados en los campos. 
	 * Para ello, "cogeremos" el id al pinchar en el botón de editar del alumno que
	 * queremos editar y por ello, {id}. Este id se detecta como un parámetro 
	 * al estar entre llaves y cambiará dependiendo de en qué alumno
	 * hayamos pinchado para editar. 
	 * 
	 * Por otro lado, para recoger ese id de la url, usamos la anotación @PathVariable
	 * que es la que se utiliza para extraer valores directamente de la url
	 * de la petición y pasarlo como argumento al controlador (por lo que
	 * debe tener el mismo nombre en ambos lados, en el GetMapping y en dentro
	 * de los parámetros)
	 * @param id del alumno a editar
	 * @param model
	 * @return
	 */
	@GetMapping("/editar/{id}")
	public String mostrarFormularioEdicion(@PathVariable("id") long id, Model model) {
		
		/* Buscamos al alumno por id y recordemos que el método findById del servicio
		 * devuelve un Optional, una vez llamado, vemos si hay presente un objeto
		 * tipo Alumno o no.
		 * Al pasar en el GetMapping el id entre llaves después del editar/ le estamos diciendo a 
		 * Spring: Oye, lo que viene detrás de editar/ no es un texto fijo, es un valor
		 * dinámico.
		 * - Si lo hay, agregamos el objeto encontrado al modelo
		 * usando el get de Optional y pintamos el formulario del archivo
		 * html llamado formulario con 
		 * los datos de dicho objeto (los campos aparecen cargados porque 
		 * queremos editar, no guardar nuevo)
		 * - Si no lo hay, llevamos directamente a pintar de nuevo la lista con todos
		 * los alumnos sin hacer nada.
		 * */
				 		
		Optional<Alumno> aEditar = alumnoServicio.findById(id);
		
		if (aEditar.isPresent()) {
			model.addAttribute("alumno", aEditar.get());
			return "formulario";
		} else {
			return "redirect:/";
		}			
	}
	
	/**
	 * Método que procesa la petición post del formulario al editar
	 * 
	 * En este caso, la anotación @modelAttribute se usa para 
	 * vincular (mapear) los datos que vienen de un formulario HTML con un objeto Java.
	 */
	@PostMapping("/editar/submit")
	public String procesarFormularioEdicion(@ModelAttribute("alumno") Alumno a) {
		
		//Llamamos al método edit con los datos del alumno pasado a
		alumnoServicio.edit(a);		
		/*
		 * Volvemos a redirigir al listado a través del controller 
		 * para pintar la lista actualizada con la modificación hecha
		 * */
		return "redirect:/";
		
	}
	/**
	 * Método que borrar un alumno por su Id
	 * @param id
	 * @return
	 */
	@GetMapping("/borrar/{id}")
	public String borrar(@PathVariable("id") long id) {
		
		Optional<Alumno> aBorrar = alumnoServicio.findById(id);		
		if (aBorrar.isPresent()) {
			alumnoServicio.delete(aBorrar.get());
		} 
		//Siempre volvemos a pintar la tabla actuallizada si se ha borrado
		//o la misma si no se ha podido hacer nada
		return "redirect:/";		
	}
}