package com.salesianostriana.dam.proyectoej10carritomemoria.error;

import jakarta.persistence.EntityNotFoundException;

public class ProductoNotFoundException extends EntityNotFoundException {

	public ProductoNotFoundException() {
		super("No hay productos con los criterios de búsqueda especificados");
	}
	
	public ProductoNotFoundException(String msg) {
		super(msg);
	}
	
	

}
