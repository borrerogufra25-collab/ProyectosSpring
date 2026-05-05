package com.salesianostriana.dam;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MainDeMentira {

	private final ProductoRepository productoRepository;
	
	@PostConstruct
	public void run() {
		
		productoRepository.save(
				Producto.builder()
					.nombre("Bocata del Manolo")
					.precio("2.5")
				.build()
				);
		
		productoRepository.findAll()
			.forEach(System.out::println);
		
		
	}
	
}
