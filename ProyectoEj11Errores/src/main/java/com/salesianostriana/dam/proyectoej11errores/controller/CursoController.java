package com.salesianostriana.dam.proyectoej11errores.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.proyectoej11errores.model.Curso;
import com.salesianostriana.dam.proyectoej11errores.service.CursoService;
import jakarta.validation.Valid;

@Controller
public class CursoController {

    @Autowired
    private CursoService service;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("cursos", service.listarTodos());
        return "index";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("curso", new Curso());
        return "formulario";
    }

    @PostMapping("/nuevo/guardar")
    public String guardarCurso(@Valid @ModelAttribute("curso") Curso curso, BindingResult bindingResult, Model model) {
        // Gestión de errores de Validación con @Valid
        if (bindingResult.hasErrors()) {
        	// Si hay errores, regresa al formulario manteniendo los mensajes
            return "formulario"; 
        }       
        service.guardar(curso);
        return "redirect:/";
    }

    @GetMapping("/matricular/{id}")
    public String matricular(@PathVariable("id") Long id) {
        // Este método puede lanzar excepciones 
    	//controladas por nuestro @ControllerAdvice
        service.matricularAlumno(id);
        return "redirect:/";
    }
}