package com.salesianostriana.dam.proyectoej09listadosedicion.repositorios;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.salesianostriana.dam.proyectoej09listadosedicion.modelo.Categoria;
import com.salesianostriana.dam.proyectoej09listadosedicion.modelo.Producto;


/**
 * Tenemos dos consultas iguales que hacen los mismo, findByCategoria
 * el primero recibe ya la categoria a buscar y es una consula
 * derivada (solo el nombre del método nos hace la consulta)
 * y el tercero, findByCategoriaId que recibe el id  de la categoría
 * a buscar pero ya anotado con la consulta @Query que dentro
 * busca la categoría por id y nos trae todos los productos de esa categoría 
 * */
public interface ProductoRepository extends JpaRepository <Producto, Long>{

	public List<Producto> findByCategoria(Categoria categoria);
	
	@Query("select p.id from Producto p")
	public List<Long> obtenerIds();
	
	@Query("select p from Producto p where p.categoria.id = ?1")
	public List<Producto> findByCategoriaId(Long categoriaId);
	
	@Query("select count(p) from Producto p where p.categoria = ?1")
	public int findNumProductosByCategoria(Categoria categoria);
	
}
