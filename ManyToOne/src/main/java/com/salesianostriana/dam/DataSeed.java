package com.salesianostriana.dam;

import org.springframework.stereotype.Component;

import com.salesianostriana.dam.modelo.Categoria;
import com.salesianostriana.dam.modelo.Producto;
import com.salesianostriana.dam.repos.CategoriaRepository;
import com.salesianostriana.dam.repos.ProductoRepository;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataSeed {
	
	private final CategoriaRepository categoriaRepository;
	private final ProductoRepository productoRepository;
	
	@PostConstruct
	public void run() {
		
		Categoria c = Categoria.builder()
				.nombre("Bebidas")
				.build();
		
		categoriaRepository.save(c);
		
		Producto p1 = Producto.builder()
				.nombre("Cruzcampo 0.0")
				.precio(1.0)
				.categoria(c)
				.build();
		
		productoRepository.save(p1);
		
		
		Producto p2 = Producto.builder()
				.nombre("Fanta de Limón")
				.precio(1.1)
				.categoria(c)
				.build();
		
		productoRepository.save(p2);
		
		productoRepository.findAll()
			.forEach(System.out::println);
		
		
		Producto rescatado = productoRepository.findById(1L)
				.orElseThrow(() -> 
					new EntityNotFoundException("""
							No se ha encontrado el producto
							"""));
		System.out.println("Producto buscado: " + rescatado.toString());
		
		
	}
	

}
