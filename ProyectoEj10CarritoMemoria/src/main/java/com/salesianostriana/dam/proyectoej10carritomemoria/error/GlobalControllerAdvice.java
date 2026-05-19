package com.salesianostriana.dam.proyectoej10carritomemoria.error;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


/*
 * Controlador especial para la gestión de errores, así
 * separamos el código de excepciones del normal
 * Se anota la clase con @ControllerAdvice
 * 
 * */
@ControllerAdvice
public class GlobalControllerAdvice {
	
	/*
	 * Devuelve un ModelAndView que es un objeto
	 * formado por un model y la vista
	 * que se pone en el return juntos, en un solo
	 * objeto
	 * */
	
	@ExceptionHandler(ProductoNotFoundException.class)
	public ModelAndView handleProductoNotFoundException(ProductoNotFoundException ex) {
		ModelAndView modelView = new ModelAndView();
        modelView.addObject("message", ex.getMessage());
        modelView.setViewName("empty-list");
        return modelView;
	}

}
