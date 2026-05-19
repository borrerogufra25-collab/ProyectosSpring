package com.salesianostriana.dam.proyectoej10carritomemoria.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.salesianostriana.dam.proyectoej10carritomemoria.modelo.Producto;
import com.salesianostriana.dam.proyectoej10carritomemoria.repos.ProductoRepository;
import com.salesianostriana.dam.proyectoej10carritomemoria.servicios.base.BaseServiceImpl;


@Service
public class ProductService extends BaseServiceImpl<Producto, Long, ProductoRepository>{


	//Métodos para buscar
	@Autowired
	private ProductoRepository productoRepository;

	public Page<Producto> buscarProductos(String termino, Pageable pageable) {
	    // Retorna solo un fragmento (ej. los primeros 20 productos)
	    return productoRepository.findByNombreContainingIgnoreCase(termino, pageable);
	}
	
}
