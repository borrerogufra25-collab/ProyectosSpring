package com.salesianostriana.dam.proyectoej13citas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.dam.proyectoej13citas.model.Cita;
import com.salesianostriana.dam.proyectoej13citas.service.CirujanoService;
import com.salesianostriana.dam.proyectoej13citas.service.CitaService;
import com.salesianostriana.dam.proyectoej13citas.service.PacienteService;

@Controller
@RequestMapping("/citas")
public class CitaController {

	//Inyectamos los servicios necesarios
    private final CitaService citaService;
    private final PacienteService pacienteService;
    private final CirujanoService cirujanoService;

    public CitaController(CitaService citaService, PacienteService pacienteService, CirujanoService cirujanoService) {
        this.citaService = citaService;
        this.pacienteService = pacienteService;
        this.cirujanoService = cirujanoService;
    }

    @GetMapping
    public String listarCitas(Model model) {
        model.addAttribute("citas", citaService.findAll());
        return "citas/listadoCita";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("cita", new Cita());
        model.addAttribute("pacientes", pacienteService.findAll());
        model.addAttribute("cirujanos", cirujanoService.findAll());
        return "citas/formCita";
    }

    @PostMapping("/nuevo/submit")
    public String procesarFormulario(@ModelAttribute("cita") Cita cita) {
        // Rescate de asociaciones
        if (cita.getCirujano() != null && cita.getCirujano().getId() != null) {
            cita.setCirujano(cirujanoService.findById(cita.getCirujano().getId()).orElse(null));
        }
        if (cita.getPaciente() != null && cita.getPaciente().getId() != null) {
            cita.setPaciente(pacienteService.findById(cita.getPaciente().getId()).orElse(null));
        }

        // Flujo directo sin try-catch. Si falla, salta al GlobalAdvice automáticamente.
        citaService.registrarCita(cita);

        return "redirect:/citas";
    }
}