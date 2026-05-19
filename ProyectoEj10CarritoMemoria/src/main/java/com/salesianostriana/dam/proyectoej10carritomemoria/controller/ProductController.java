package com.salesianostriana.dam.proyectoej10carritomemoria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.proyectoej10carritomemoria.servicios.ProductService;
import jakarta.servlet.http.HttpSession;

@Controller
public class ProductController {
	
	@Autowired
	HttpSession session;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping({"/", "/list"})
	public String productList(Model model) {
		
		model.addAttribute("productos", productService.findAll());

		/*La siguiente línea viene del último método, que se dedica a 
		 * buscar, para que este método, muestre también el listado de 
		productos cuando se han buscado, añadimos al model el objeto 
		tipo bean de búsqueda cuando se está buscando algún producto*/
		//model.addAttribute("searchForm", new SearchBean());
		return "list";
		
	}

}
