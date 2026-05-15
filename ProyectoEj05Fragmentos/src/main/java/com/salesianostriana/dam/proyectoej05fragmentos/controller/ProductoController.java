package com.salesianostriana.dam.proyectoej05fragmentos.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.salesianostriana.dam.proyectoej05fragmentos.modelo.Producto;

@Controller
public class ProductoController {

	
	/*Para la primera versión con index con el css dentro del propio html*/
	
    @GetMapping("/producto")
    public String verProducto(Model model) {
  
        model.addAttribute("producto", new Producto("Katana de Hattori Hanzo", 1200.50));
        return "index"; 
    }
    
    /*
     * Para la segunda versión index2, con los css separados del html en 
     * archivo dentro de css estilos.css
     * 
     * */
    @GetMapping("/producto2")
    public String verProducto2(Model model) {
  
        model.addAttribute("producto", new Producto("Katana de Hattori Hanzo", 1200.50));
        return "index2"; 
    }
    
    @GetMapping("/contacto")
    public String contacto() {
        
        return "contacto"; 
    }
    
    @GetMapping("/contacto2")
    public String contacto2() {
        
        return "contacto2"; 
    }
}

