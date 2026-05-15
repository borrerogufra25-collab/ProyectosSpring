package com.salesianostriana.dam.proyectoej09listadosedicion.servicios;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.proyectoej09listadosedicion.modelo.Categoria;
import com.salesianostriana.dam.proyectoej09listadosedicion.modelo.Producto;
import com.salesianostriana.dam.proyectoej09listadosedicion.repositorios.ProductoRepository;
import com.salesianostriana.dam.proyectoej09listadosedicion.servicios.base.BaseServiceImpl;

@Service
public class ProductoService extends BaseServiceImpl<Producto, Long, ProductoRepository> {

	public int numeroProductosCategoria(Categoria categoria) {
		return repository.findNumProductosByCategoria(categoria);
	}
	
	
	/*
	 * Este método sirve para obtener un número de productos aleatorios.
	 * Lo realizamos en Java para abstraernos mejor de la base de datos
	 * concreta que vamos a usar.
	 * Algunos SGBDR nos permitirían usar la función RANDOM, y podríamos
	 * hacer esta consulta de forma nativa.
	 */
	public List<Producto> obtenerProductosAleatorios(int numero) {
		// Obtenemos los ids de todos los productos
		List<Long> listaIds = repository.obtenerIds();
		// Los desordenamos 
		Collections.shuffle(listaIds);
		// Nos quedamos con los N primeros, con N = numero
		listaIds = listaIds.stream().limit(numero).collect(Collectors.toList());
		// Buscamos los productos con esos IDs y devolvemos la lista
		return repository.findAllById(listaIds);
	}
	
	
	public List<Producto> findAllByCategoria(Categoria categoria) {
		return repository.findByCategoriaId(categoria.getId());
	}
	
	public List<Producto> findByCategoriaId(Long idCategoria) {
		return repository.findByCategoriaId(idCategoria);
	}
	
	
}
