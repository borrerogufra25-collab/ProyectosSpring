package com.salesianostriana.dam.proyectoej13citas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        // Renderiza la plantilla index.html que sirve de menú principal
        return "index";
    }
}