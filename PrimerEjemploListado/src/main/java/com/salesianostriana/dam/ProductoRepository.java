package com.salesianostriana.dam;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class ProductoRepository {
	
	
	private List<Producto> productos;
	
	@PostConstruct
	private void init() {
		productos  = new ArrayList<>();
		productos.add(new Producto("Macbook Pro M5 Pro", 2599.99));
		productos.add(new Producto("Botellín de Cruzcampo", 1.5));
	}
	
	public List<Producto> getAll() {
		return productos;
	}
	

}
