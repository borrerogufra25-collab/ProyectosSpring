package com.salesianostriana.dam;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataSeed {
	
	private final ProductoRepository productoRepository;
	
	@PostConstruct
	public void init() {
		
		productoRepository.save(
				Producto.builder()
				.nombre("Producto 3")
				.precio("1.2")
				.build()
				);

		productoRepository.save(
				Producto.builder()
				.nombre("Producto 4")
				.precio("2.3")
				.build()
				);

		productoRepository.save(
				Producto.builder()
				.nombre("Producto 5")
				.precio("3.4")
				.build()
				);

		
	}
	

}
