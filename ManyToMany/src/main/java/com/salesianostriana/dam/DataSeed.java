package com.salesianostriana.dam;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.salesianostriana.dam.modelo.Categoria;
import com.salesianostriana.dam.modelo.Producto;
import com.salesianostriana.dam.modelo.Tag;
import com.salesianostriana.dam.repos.CategoriaRepository;
import com.salesianostriana.dam.repos.ProductoRepository;
import com.salesianostriana.dam.repos.TagRepository;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataSeed {
	
	private final CategoriaRepository categoriaRepository;
	private final ProductoRepository productoRepository;
	private final TagRepository tagRepository;
	
	@PostConstruct
	public void run() {
		
		Categoria c = Categoria.builder()
				.nombre("Bebidas")
				.build();
		
		categoriaRepository.save(c);
		
		Tag t1 = Tag.builder()
				.nombre("Fresquita")
				.build();
		
		Tag t2 = Tag.builder()
				.nombre("De Sevilla")
				.build();
		
		Tag t3 = Tag.builder()
				.nombre("Sin alcohol")
				.build();
		
		tagRepository.saveAll(List.of(t1,t2,t3));
		
		Producto p1 = Producto.builder()
				.nombre("Cruzcampo 0.0")
				.precio(1.0)
				//.categoria(c)
				.build();
		
		//p1.getTags().addAll(List.of(t1,t2,t3));
		//p1.getTags().add(t1);
		//p1.getTags().add(t2);
		//p1.getTags().add(t3);
		
		p1.addTag(t1);
		p1.addTag(t2);
		p1.addTag(t3);
		
		p1.addToCategoria(c);
		
		productoRepository.save(p1);
		
		
		Producto p2 = Producto.builder()
				.nombre("Fanta de Limón")
				.precio(1.1)
				//.categoria(c)
				.build();
		
		p2.addToCategoria(c);
		
		//p2.getTags().add(t1);
		p2.addTag(t1);
		
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
		
		
		/*Tag tag1Buscada = tagRepository.findById(1L)
				.orElseThrow(() -> new EntityNotFoundException("Tag no encontrada"));
		*/
		Tag tag1Buscada = t1;
		System.out.println("================================");
		System.out.println("Tag: " + tag1Buscada.getNombre());
		nombresProducto = 
				tag1Buscada.getProductos()
					.stream()
					.map(Producto::getNombre)
					.collect(Collectors.joining(","));
		System.out.println("Productos del tag: " + nombresProducto);
		
		
	}
	

}
