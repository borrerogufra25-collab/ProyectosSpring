package com.salesianostriana.dam;

import java.util.stream.Collectors;

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
				//.categoria(c)
				.build();
		
		p1.addToCategoria(c);
		
		productoRepository.save(p1);
		
		
		Producto p2 = Producto.builder()
				.nombre("Fanta de Limón")
				.precio(1.1)
				//.categoria(c)
				.build();
		
		p2.addToCategoria(c);
		
		productoRepository.save(p2);
		
		productoRepository.findAll()
			.forEach(System.out::println);
		
		System.out.println("Categoria: " + c.toString());
		String nombresProducto = 
				c.getProductos()
					.stream()
					.map(Producto::getNombre)
					.collect(Collectors.joining(","));
		System.out.println("Productos de %s: %s"
				.formatted(c.getNombre(), nombresProducto));
		
		
		
	}
	

}
