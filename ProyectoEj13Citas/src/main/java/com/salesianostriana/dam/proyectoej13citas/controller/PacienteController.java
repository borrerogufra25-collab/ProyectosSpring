package com.salesianostriana.dam.proyectoej13citas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.dam.proyectoej13citas.model.Paciente;
import com.salesianostriana.dam.proyectoej13citas.service.PacienteService;

@Controller
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    // Inyección limpia por constructor
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    /**
     * Devuelve el listado de todos los pacientes mapeado con 'pacientes'
     * que es lo que busca el th:each="p : ${pacientes}"
     */
    @GetMapping
    public String listarPacientes(Model model) {
        model.addAttribute("pacientes", pacienteService.findAll());
        return "pacientes/listadoPaciente"; // Busca la ruta exacta de tu archivo HTML
    }

    /**
     * Muestra el formulario específico para un nuevo paciente
     */
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("paciente", new Paciente());
        return "pacientes/formPaciente";
    }

    /**
     * Procesa y guarda los datos del formulario de paciente
     */
    @PostMapping("/nuevo/submit")
    public String procesarFormulario(@ModelAttribute("paciente") Paciente paciente) {
        pacienteService.save(paciente);
        return "redirect:/pacientes";
    }
}