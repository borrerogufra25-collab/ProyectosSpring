package com.salesianostriana.dam.proyectoej02expresiones;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

    @GetMapping("/ejemplo-expresiones")
    public String showExpressions(Model model) {
        // Creamos un producto de prueba
        Product sampleProduct = new Product(
            101, 
            "Teclado Mecánico RGB", 
            89.99, 
            false, 
            "Un teclado excelente para programadores novatos."
        );

        // Pasamos datos al modelo
        model.addAttribute("product", sampleProduct);
        model.addAttribute("welcomeMsg", "Bienvenido a la tienda oficial");
        
        return "expressions"; // Nombre del archivo HTML en src/main/resources/templates
    }
}
