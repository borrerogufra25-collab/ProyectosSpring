package com.salesianostriana.dam;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.dam.usuario.User;

@Controller
@RequestMapping("/producto/")
public class ProductoController {
	
	@GetMapping
	public String index() {
		return "producto/producto";
	}
	
	@GetMapping("/list")
	public String list(Model model,
			@AuthenticationPrincipal User usuario
			) {
		model.addAttribute("productos", "lista de productos");
		System.out.println("Usuario: " + usuario.getUsername());
		System.out.println("Usuario: " + usuario.toString());
		model.addAttribute("usuario", usuario.getUsername());
		return "producto/list";
	}

	
	
	
}
