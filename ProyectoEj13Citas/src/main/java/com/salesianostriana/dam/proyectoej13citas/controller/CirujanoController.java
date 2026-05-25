package com.salesianostriana.dam.proyectoej13citas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.dam.proyectoej13citas.model.Cirujano;
import com.salesianostriana.dam.proyectoej13citas.service.CirujanoService;

@Controller
@RequestMapping("/cirujanos")
public class CirujanoController {

    private final CirujanoService cirujanoService;

    public CirujanoController(CirujanoService cirujanoService) {
        this.cirujanoService = cirujanoService;
    }

    /**
     * Alimenta la vista con la lista de 'cirujanos'
     */
    @GetMapping
    public String listarCirujanos(Model model) {
        model.addAttribute("cirujanos", cirujanoService.findAll());
        return "cirujanos/listadoCirujano";
    }

    /**
     * Muestra el formulario para registrar un especialista
     */
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("cirujano", new Cirujano());
        return "cirujanos/formCirujano";
    }

    /**
     * Procesa y guarda los datos del formulario de cirujano
     */
    @PostMapping("/nuevo/submit")
    public String procesarFormulario(@ModelAttribute("cirujano") Cirujano cirujano) {
        cirujanoService.save(cirujano);
        return "redirect:/cirujanos";
    }
}